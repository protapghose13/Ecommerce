'use strict';

const table_conf = {
  PageNumber : "_page_number",
  PageSize : "_page_size"
};

const pages_conf = [ 
                     { value : 5, id : 0 }, 
                     { value : 10, id : 1 }, 
                     { value : 15, id : 2 }, 
                     { value : 20, id : 3 } 
                  ];

var allpages = [0, 1, 2, 3];

function isValid(value){
  return (value !== undefined && value !== null && value !== '');
}

function getPageSize(key){
  var value = localStorage.getItem(key);
  return (isValid(value) ? pages_conf[value] : pages_conf[0]);
}

function set(key, value){
  return localStorage.setItem(key, value);
}

function tablePageConf(pageScope){
  pageScope.allPageSizes = pages_conf;
  pageScope.selectedPageSize = getPageSize(table_conf.PageSize);
  pageScope.allpages = allpages;
}

function loadPage(){
  location.reload();
}

// Register `productList` component, along with its associated controller and template
angular.
  module('productList').
  component('productList', {
    templateUrl: 'product-list/product-list.template.html',
    controller: ['$routeParams', 'productService', '$scope',
      function ProductListController($routeParams, productService, $scope) {
        onLoad();

        function onLoad(){
          tablePageConf($scope);
          var currentPage = (isValid($routeParams.page) ? $routeParams.page : 0);
          productService.getProductsOfCurrentPage($scope.selectedPageSize.value, currentPage)
                                      .then(function (products) {
              $scope.products = products.allProducts;
              $scope.currentPage = products.currentPage;
              $scope.totalPages = products.totalPages;
              $scope.allPageSizes = pages_conf;
              $scope.selectedPageSize = getPageSize(table_conf.PageSize);
              $scope.allpages = [];

              var offset = 3;
              for(var i = Math.max(0, currentPage - offset); i < Math.min(products.totalPages, (+currentPage + +offset + +1)); i++){
                $scope.allpages.push(i);
              }
          });

          $scope.orderProp = "name";
        }
        
        $scope.clicked = function(product){
          window.location = "#!/products/edit/" + product.id;
        }

        $scope.onChangePageSize = function() {
          set(table_conf.PageSize ,$scope.selectedPageSize.id);
          loadPage();
        }

        $scope.getDataofPage = function(page){
          window.location = "/#!/products/" + page;
        }
      }
    ]
  });
