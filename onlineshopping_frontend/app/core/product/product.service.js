'use strict';

angular.
  module('core.product').
  factory('productService', ['Restangular',
    function(Restangular) {
      function reloadPage(){
        location.reload();
      }

      function isValid(value){
        return (value !== undefined && value !== null);
      }

      function getProducts(){
          return Restangular.all('products').getList();
      }

      function getProduct(productId){
        return Restangular.one('products', productId).get();
      }

      function createProduct(product){
        var products = Restangular.all('products');
        products.post(product);
        window.location = 'http://localhost:8000/#!/products';
        reloadPage();
      }

      function updateProduct(product){
        if(isValid(product) && isValid(product.id)){
          Restangular.one('products', product.id).get().then(function(prod){
            if( isValid(prod) ){
              prod.name = product.name;
              prod.description = product.description;
              prod.code = product.code;
              prod.quantity = product.quantity;
              prod.unitPrice = product.unitPrice;
              prod.categoryId = product.categoryId;
              prod.brandId = product.brandId;
              prod.put();
              window.location = 'http://localhost:8000/#!/products';
              reloadPage();
            }
          });
        }
      }

      function deleteProduct(productId){
        if(isValid(productId)){
          Restangular.one('products', productId).get().then(function(prod){
            if(isValid(prod)){
              prod.remove();
              window.location = 'http://localhost:8000/#!/products';
              reloadPage();
            }
          });
        }
      }

      return {
        getProducts : getProducts,
        getProduct : getProduct,
        createProduct : createProduct,
        updateProduct : updateProduct,
        deleteProduct : deleteProduct
      };
    }
  ]);

// (function() {
// 	angular.module('core.employee').factory('employeeService',
// 		['Restangular', function(Restangular) {
//       alert("dfdsfsdfsd");
// 			var service = Restangular.service("employees");  
//       // I can add custom methods to my Students service
//       // by adding functions here service
//       service.validateData = function(student) {
//           //validate student data
//       }
//       return service;
// 		}]);
// }());
