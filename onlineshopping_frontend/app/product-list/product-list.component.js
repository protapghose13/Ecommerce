'use strict';

// Register `productList` component, along with its associated controller and template
angular.
  module('productList').
  component('productList', {
    templateUrl: 'product-list/product-list.template.html',
    controller: ['productService', '$scope',
      function ProductListController(productService, $scope) {
        productService.getProducts().then(function (products) {
          $scope.products = products;
          console.log(products);
        });
        $scope.orderProp = "name";

        $scope.clicked = function(product){
          window.location = "#!/products/edit/" + product.id;
        }
      }
    ]
  });
