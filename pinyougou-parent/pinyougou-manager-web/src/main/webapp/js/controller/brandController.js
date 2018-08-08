app.controller('brandController',function ($scope,$controller,brandService) {
    //继承
    $controller("baseController",{$scope:$scope});

    //查询品牌列表
    $scope.findAll=function () {
        brandService.findAll().success(
            function (response) {
                $scope.list=response;
            }
        );
    }

    $scope.findOne=function(id){
        brandService.findOne(id).success(
            function (response) {
                $scope.entity=response;
            }
        );
    }

    $scope.findPage=function (page,rows) {
        brandService.findPage(page,rows).success(
            function (response) {
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;
            }
        );
    }

    $scope.save=function(){
        var object=brandService.add($scope.entity);
        if($scope.entity.id!=null) {//如果有ID
            object = brandService.update($scope.entity);
        }
        object.success(
            function(response){
                if(response.success){
                    //重新查询
                    $scope.reloadList();//重新加载
                }else{
                    alert(response.message);
                }
            }
        );
    }


    $scope.dele=function () {
        brandService.dele($scope.selectIds).success(function (response) {
            if(response.success){
                $scope.reloadList();
            }
        })
    }

    $scope.searchEntity={};//定义搜索对象
    $scope.search=function (page,rows) {
        brandService.search(page,rows,$scope.searchEntity).success(function (response) {
            $scope.paginationConf.totalItems=response.total;//总记录数
            $scope.list=response.rows;
        })
    }


});