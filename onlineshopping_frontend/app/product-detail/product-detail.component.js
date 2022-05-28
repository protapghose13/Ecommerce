'use strict';

function isValid(value){
  return (value !== undefined && value !== null);
}

angular.
  module('productDetail').
  directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

// Register `productDetail` component, along with its associated controller and template
angular.
  module('productDetail').
  component('productDetail', {
    templateUrl: 'product-detail/product-detail.template.html',
    controller: ['$routeParams', 'productService', 'categoryService', 'brandService', '$scope', '$http',
      function ProductDetailController($routeParams, productService, categoryService, brandService, $scope, $http) {
        
        onload();

        function onload(){
          if(isValid($routeParams.productId)){
            productService.getProduct($routeParams.productId).then(function (product) {
              if(isValid(product)){
                // TODO : mapping
                $scope.id = product.id;
                $scope.name = product.name;
                $scope.description = product.description;
                $scope.code = product.code;
                $scope.quantity = product.quantity;
                $scope.unitPrice = product.unitPrice;
                $scope.selectedCategory = product.category;
                $scope.selectedBrand = product.brand;

                if(isValid(product.categoryId)){
                  categoryService.getCategories().then(function (categories) {
                    $scope.allCategories = categories;
                  });
                }
      
                if(isValid(product.brandId)){
                  brandService.getBrands().then(function(brands){
                    $scope.allBrands = brands;
                  });
                }

                loadImages();

                function loadImages(){
                  var imgUrl = 'http://localhost:8090/api/v1/products/images/all/' + $scope.id;
                  $http({
                    method: 'GET',
                    url: imgUrl
                  }).then(function successCallback(response) {
                    console.log(response);
                    $scope.allProductImages = response.data;
                    $scope.showCaseImage = $scope.allProductImages[0];
                    console.log($scope.allProductImages);
                  }, function errorCallback(response) {
                    console.log(response);
                  });
                }

                $scope.detailHeader = "Product Details";
              }
              else{
                alert("Route parameter is not valid.");
                window.location.back();
              }
            });
          }
          else{
            alert("Route parameter is not valid.");
            window.location.back();
          }

          categoryService.getCategories().then(function (categories) {
            $scope.allCategories = categories;
          });

          brandService.getBrands().then(function(brands){
            $scope.allBrands = brands;
          });
        }

        $scope.setShowCaseImage = function(image){
          $scope.showCaseImage = image;  
        }

        $scope.saveProduct = function(){
          const product = {
            id : $scope.id,
            name : $scope.name,
            description : $scope.description,
            code : $scope.code,
            quantity : $scope.quantity,
            unitPrice : $scope.unitPrice,
            categoryId : $scope.selectedCategory.id,
            brandId : $scope.selectedBrand.id
          };
          productService.updateProduct(product);
        }

        $scope.deleteProduct = function(){
          productService.deleteProduct($scope.id);
        }

        $scope.saveImage = function(){
          if(isValid($scope.myFile)){
            var file = $scope.myFile;
            var uploadUrl = "http://localhost:8090/api/v1/products/image/create/" + $scope.id;

            var fd = new FormData();
            fd.append('file', file);

            $http.post(uploadUrl, fd, {
              transformRequest: angular.identity,
              headers: {'Content-Type': undefined}
            }).then(
              function(response){
                console.log(response);
                $scope.allProductImages = response.data;
                $scope.showCaseImage = $scope.allProductImages[0];
              },
              function(response){
                console.log(response);
              }
            );
          }
        }

        $scope.comeBack = function comeBack(){
          window.history.back();
        }
      }
    ]
  });