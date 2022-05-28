'use strict';

function isValid(value){
  return (value !== undefined && value !== null);
}

// Register a custom directive fileModel for uploading files
angular.
  module('productCreate').
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

// Register `productCreate` component, along with its associated controller and template
angular.
  module('productCreate').
  component('productCreate', {
    templateUrl: 'product-create/product-create.template.html',
    controller: ['$routeParams', 'productService', 'categoryService', 'brandService', '$scope', '$http',
      function ProductCreateController($routeParams, productService, categoryService, brandService, $scope, $http) {

        onload();

        function onload(){
          $scope.detailHeader = "Add new product";

          categoryService.getCategories().then(function (categories) {
            $scope.allCategories = categories;
          });

          brandService.getBrands().then(function(brands){
            $scope.allBrands = brands;
          });
        }

        $scope.saveProduct = function(){
          const product = {
            name : $scope.name,
            description : $scope.description,
            code : $scope.code,
            quantity : $scope.quantity,
            unitPrice : $scope.unitPrice,
            categoryId : $scope.selectedCategory.id,
            brandId : $scope.selectedBrand.id
          };
          
          productService.createProduct(product).then(function(product){
            
            var file = $scope.myFile;
            var uploadUrl = "http://localhost:8090/api/v1/products/image/create/" + product.id;

            var fd = new FormData();
            fd.append('file', file);

            $http.post(uploadUrl, fd, {
              transformRequest: angular.identity,
              headers: {'Content-Type': undefined}
            }).then(
              function(response){
                console.log(response);
                $scope.allProductImages = response.data;
                window.history.back();
              },
              function(response){
                console.log(response);
                window.history.back();
              }
            );

          });
        }
      }
    ]
  });