'use strict';

angular.
  module('core.category').
  factory('categoryService', ['Restangular',
    function(Restangular) {
      function reloadPage(){
        location.reload();
      }

      function isValid(value){
        return (value !== undefined && value !== null);
      }

      function getCategories(){
          return Restangular.all('categories').getList();
      }

      function getCategory(productId){
        return Restangular.one('categories', productId).get();
      }

      function createCategory(product){
        var products = Restangular.all('categories');
        products.post(product);
        window.location = 'http://localhost:8000/#!/products';
        reloadPage();
      }

      function updateCategory(product){
        if(isValid(product) && isValid(product.id)){
          Restangular.one('categories', product.id).get().then(function(prod){
            if( isValid(prod) ){
              prod.firstName = product.firstName;
              prod.lastName = product.lastName;
              prod.emailId = product.emailId;
              prod.put();
              reloadPage();
            }
          });
        }
      }

      function deleteCategory(productId){
        if(isValid(productId)){
          Restangular.one('categories', productId).get().then(function(prod){
            if(isValid(prod)){
              prod.remove();
              window.location = '';
              reloadPage();
            }
          });
        }
      }

      return {
        getCategories : getCategories,
        getCategory : getCategory,
        createCategory : createCategory,
        updateCategory : updateCategory,
        deleteCategory : deleteCategory
      };
    }
  ]);
