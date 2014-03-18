var app = angular.module("myApp",[]);
app.controller("NewsListCtrl", function($scope, $http) {
  $http.get('news.do').success(function(data) {
      alert(data);
      $scope.newsList = data;
  });
});

app.controller("SlideCtrl", function($scope) {
  $scope.slides = [
    {"imgurl": "../img/function/fun1.jpg"},
    {"imgurl": "../img/function/fun2.jpg"},
    {"imgurl": "../img/function/fun3.jpg"},
    {"imgurl": "../img/function/fun4.jpg"}
  ];
});

app.controller("DonationRecordCtrl", function($scope){
  var records = new Array(50);
  for(var  i = 0; i < 50; i++) {
    var record = Record(i + 1);
    records[i] = record;
  }
  $scope.records = records;
});

function Record(id) {
  return {
    "id" : id,
    "date" : "2014-01-01",
    "location" : "中大五院",
    "status" : "通过"
  }
}