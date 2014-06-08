'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', ['ngResource'])
        .factory('Resources', ['$resource', function($resource) {

                var Resources = function() {
                    var _private = this;

                    _private.gear = $resource('http://localhost:8080/v1/app/gear:uuid', {}, {
                        query: {
                            url: 'http://localhost:8080/v1/app/gear/all',
                            method: 'GET',
                            isArray: true
                        },
                        get: {
                            method: 'GET',
                            isArray: false
                        },
                        update: {method: 'PUT'},
                        create: {method: 'POST'},
                        delete: {method: 'DELETE'}
                    });

                    _private.user = $resource('http://localhost:8080/v1/app/employee:uuid', {}, {
                        query: {
                            url: 'http://localhost:8080/v1/app/employee/all',
                            method: 'GET',
                            isArray: true
                        },
                        get: {
                            method: 'GET',
                            isArray: false
                        },
                        update: {method: 'PUT'},
                        create: {method: 'POST'},
                        delete: {method: 'DELETE'}
                    });

                    _private.withdrawal = $resource('http://localhost:8080/v1/app/withdrawal:uuid', {}, {
                        query: {
                            url: 'http://localhost:8080/v1/app/withdrawal',
                            method: 'GET',
                            isArray: true
                        },
                        get: {
                            method: 'GET',
                            isArray: false
                        },
                        update: {method: 'PUT'},
                        create: {
                             url: 'http://localhost:8080/v1/app/withdrawal',
                            method: 'PUT'
                        },
                        delete: {method: 'DELETE'}
                    });
                };

                return new Resources();
            }])
        .service('ModalService', ['$modal', function($modal) {



            }]);

