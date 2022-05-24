'use strict';

const btnType = {
  Create : "Create",
  Save : "Save"
}

function isValid(value){
  return (value !== undefined && value !== null);
}

// Register `productDetail` component, along with its associated controller and template
angular.
  module('productDetail').
  component('productDetail', {
    templateUrl: 'product-detail/product-detail.template.html',
    controller: ['$routeParams', 'productService', 'categoryService', 'brandService', '$scope',
      function ProductDetailController($routeParams, productService, categoryService, brandService, $scope) {
        
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

                $scope.detailHeader = "Product Details";
                $scope.submitButtonValue = btnType.Save;
              }
              else{
                $scope.detailHeader = "Add new product";
                $scope.submitButtonValue = btnType.Create;
                window.location = 'http://localhost:8000/#!/products';
              }
            });
          }
          else{
            $scope.detailHeader = "Add new product";
            $scope.submitButtonValue = btnType.Create;
          }

          categoryService.getCategories().then(function (categories) {
            $scope.allCategories = categories;
          });

          brandService.getBrands().then(function(brands){
            $scope.allBrands = brands;
          });
        }

        $scope.saveProduct = function(){
          if(btnType.Create === $scope.submitButtonValue){
            const product = {
              name : $scope.name,
              description : $scope.description,
              code : $scope.code,
              quantity : $scope.quantity,
              unitPrice : $scope.unitPrice,
              categoryId : $scope.selectedCategory.id,
              brandId : $scope.selectedBrand.id
            };
            productService.createProduct(product);
          }
          else{
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
        }

        $scope.deleteProduct = function(){
          productService.deleteProduct($scope.id);
        }

        $scope.comeBack = function comeBack(){
          window.history.back();
        };
      }
    ]
  });