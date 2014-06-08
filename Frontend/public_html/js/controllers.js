'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
        .controller('gearsController', ['$scope', 'Resources', function($scope, Resources) {

                angular.element(document).ready(function() {
                    $scope.getAll();
                });

                $scope.gears = [];
                $scope.foundGear = null;
                $scope.gear = {};
                $scope.gearScope = {uuid: null, fname: 'asas', lname: 'nulsdl', age: null};
                $scope.lastCreatedGear = {};

                $scope.getAll = function() {
                    Resources.gear.query(function(response) {

                        $scope.gears = [];
                        $scope.gears = response;
                    });

                };
                $scope.getById = function(uuid) {
                    Resources.gear.get({uuid: uuid}, function(response) { //success
                        $scope.foundGear = response;
                        console.log(response);
                    },
                            function(response) { // error
                                console.log(response);
                                alert('ERROR: ' + response.status);
                            });
                };

                $scope.create = function() {
                    Resources.gear.create($scope.gear, function(response) {
                        console.log(response);
                        console.log($scope.gears);
                        $scope.lastCreatedGear = response;
                        $scope.getAll();
                        $scope.gear = {};

                    });
                };

                $scope.update = function() {
                    Resources.gear.update($scope.foundGear);
                    $scope.foundGear = null;
                    $scope.gears = [];
                    $scope.getAll();
                };

                $scope.delete = function() {
                    Resources.gear.delete({uuid: $scope.foundGear.uuid});
                    $scope.gears = [];
                    $scope.getAll();
                    $scope.foundGear = null;
                };

                //methods
                $scope.selectRow = function(selected) {
                    alert(selected.uuid);
                };



            }])
        .controller('modalController', ['$scope', '$modal', 'Resources', function($scope, $modal) {
                $scope.items = ['item1', 'item2', 'item3'];

                $scope.open = function(size) {

                    var modalInstance = $modal.open({
                        templateUrl: 'modal.html',
                        controller: function($scope, $modalInstance, items) {
                            $scope.items = items;
                            $scope.selected = {
                                item: $scope.items[0]
                            };

                            $scope.ok = function() {
                                $modalInstance.close($scope.selected.item);
                            };

                            $scope.cancel = function() {
                                $modalInstance.dismiss('cancel');
                            };
                        },
                        size: size,
                        resolve: {
                            items: function() {
                                return $scope.items;
                            }
                        }
                    });
                    modalInstance.result.then(function(selectedItem) {
                        $scope.selected = selectedItem;
                    }, function() {

                    });
                };
            }])
        .controller('withdrawalController', ['$scope', 'Resources', function($scope, Resources) {
                $scope.users = [];
                $scope.gears = [];
                $scope.withdrawals = [];
                
                $scope.withdrawal = {worker: $scope.selectedUser, gear: $scope.selectedgear};

                $scope.selectedUser = null;
                $scope.selectedgear = null;

                $scope.setUser = function(user) {
                    $scope.selectedUser = user;
                };

                $scope.setGear = function(gear) {
                    $scope.selectedgear = gear;
                };

                angular.element(document).ready(function() {
                    $scope.getAllUsers();
                    $scope.getAllGears();
                    $scope.getAllWithdrawals();
                });

                $scope.getAllUsers = function() {
                    Resources.user.query(function(response) {
                        $scope.users = [];
                        $scope.users = response;
                    });

                };
                $scope.getAllGears = function() {
                    Resources.gear.query(function(response) {

                        $scope.gears = [];
                        $scope.gears = response;
                    });

                };
                
                $scope.getAllWithdrawals = function() {
                    Resources.withdrawal.query(function(response) {
                        $scope.withdrawals = response;
                         console.log(angular.toJson(response));
                    });

                };
                

                $scope.finish = function() {
                    if ($scope.selectedUser !== null && $scope.selectedgear !== null) {

                        var withdrawal = {worker: $scope.selectedUser, gear: $scope.selectedgear};

                        console.log(angular.toJson(withdrawal));
                        Resources.withdrawal.create(withdrawal);
          
                        $scope.getAllWithdrawals();
          
                        $scope.selectedUser = null;
                        $scope.selectedgear = null;
                    }
                };
                $scope.cancel = function() {
                    $scope.selectedUser = null;
                    $scope.selectedgear = null;

                };

            }])
        .controller('usersController', ['$scope', 'Resources', function($scope, Resources) {

                $scope.users = [];
                angular.element(document).ready(function() {
                    $scope.getAll();
                });

                $scope.getAll = function() {
                    Resources.user.query(function(response) {
                        $scope.users = [];
                        $scope.users = response;
                    });

                };

            }]);

