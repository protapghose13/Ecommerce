'use strict';

// Register `phoneList` component, along with its associated controller and template
angular.
  module('phoneListTable').
  component('phoneListTable', {
    templateUrl: 'phone-list-table/phone-list-table.template.html',
    controller: ['Phone', '$scope',
      function PhoneListController(Phone, $scope) {
        this.phones = Phone.query();
        this.orderProp = 'age';

        $scope.clicked = function(phone){
          window.location = "#!/phones/" + phone.id;
        }
      }
    ]
  });
