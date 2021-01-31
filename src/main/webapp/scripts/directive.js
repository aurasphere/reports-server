app
		.directive(
				'loading',
				function() {
					return {
						transclude : true,
						template : "<div class='align-middle'>"
								+ "<div data-ng-show='loading'><i class='fa fa-spinner fa-spin loading'></i></div>"
								+ "<div data-ng-hide='loading'><data-ng-transclude></data-ng-transclude></div>"
								+ "</div>",
						scope : {
							loading : "="
						},
						replace : true
					};
				});

// Directive for limiting a numering input to the max value.
app
		.directive(
				"limitToMax",
				function() {
					return {
						priority : 1,
						link : function($scope, element, attributes) {
							element
									.on(
											"blur",
											function(e) {
												var rowName = $scope.$parent.timesheet.rows[$scope.$parent.$index].name;
												var maxNumberOfHours;

												if (rowName == 'Other'
														|| rowName.startsWith('Overtime')) {
													maxNumberOfHours = 24;
												} else {
													maxNumberOfHours = 8;
												}

												if (Number(element.val()) > maxNumberOfHours
														&& e.keyCode != 46 // delete
														&& e.keyCode != 8 // backspace
												) {
													e.preventDefault();
													element
															.val(maxNumberOfHours);
													$scope.hour.value = maxNumberOfHours;
													$scope.$apply();
												}
											});
						}
					};
				});

// Hides zero in report input fields.
app.directive('hideZero', function() {
	return {
		require : 'ngModel',
		link : function(scope, element, attrs, ngModel) {
			ngModel.$formatters.push(function(inputValue) {
				if (inputValue == 0) {
					return '';
				}
				return inputValue;
			});
			ngModel.$parsers.push(function(inputValue) {
				if (inputValue == 0) {
					ngModel.$setViewValue('');
					ngModel.$render();
				}
				return inputValue;
			});
		}
	};
});

// Formats currencies in expense account input fields.
app.directive('autonumeric', [function() {
    return {
      // Require ng-model in the element attribute for watching changes.
      require: '?ngModel',
      // This directive only works when used in element's attribute (e.g: autonumeric)
      restrict: 'A',
      compile: function(tElm, tAttrs) {

        var isTextInput = tElm.is('input:text');

        return function(scope, elm, attrs, controller) {
          // Helper method to update autoNumeric with new value.
          var updateElement = function(autonumeric, newVal) {
            // Only set value if value is numeric
            if ($.isNumeric(newVal))
            	autonumeric.set(newVal);
          };
          
          // Initialize element as autoNumeric with options (default is a currency).
          var autonumeric = new AutoNumeric(elm[0], ["euroPos", scope.$eval(attrs.autonumeric)]);

          // if element has controller, wire it (only for <input type="text" />)
          if (controller && isTextInput) {
            // watch for external changes to model and re-render element
            scope.$watch(tAttrs.ngModel, function(current, old) {
              controller.$render();
            });
            // render element as autoNumeric
            controller.$render = function() {
              updateElement(autonumeric, controller.$viewValue);
            }
            // Detect changes on element and update model.
            elm.on('change', function(e) {
              scope.$apply(function() {
                controller.$setViewValue(autonumeric.get());
              });
            });
          } else {
            // Listen for changes to value changes and re-render element.
            // Useful when binding to a readonly input field.
            if (isTextInput) {
              attrs.$observe('value', function(val) {
                updateElement(autonumeric, val);
              });
            }
          }
        }
      } // compile
    } // return
  }])
  .controller('ctrl', [
  '$scope',
  function($scope) {
  }
]);

app.directive(
		'autoDismissAlert',
		function() {
			return {
				template : '<div class="row alert my-3 fade-out-animation" data-ng-class="error ? \'alert-danger\' : \'alert-success\'" role="alert" data-ng-show="showAlert" data-ng-bind="message"></div>',
				replace : true
			};
		});