/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 17);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports) {

module.exports = "<div class=\"container\"></div>"

/***/ }),
/* 1 */
/***/ (function(module, exports) {

const indexController = {
    headerAction() {
      const pagelist = ['home.html','order.html','myOrder.html', 'login.html', 'welcome.html']
      $('header li').on('click', function(){
        location.href = pagelist[$(this).index()]
        $(this).addClass('active').siblings().removeClass('active');
      })
      switch(location.pathname.substr(1)){
        case  "public/home.html" : $('header li').eq(0).addClass("active");break;
        case  "public/order.html" : $('header li').eq(1).addClass("active");break;
        case  "public/myOrder.html" : $('header li').eq(2).addClass("active");break;
        // case  "changeTable.html" : $('header li').eq(3).addClass("active");break;
        case  "public/login.html" : $('header li').eq(3).addClass("active");break;
        case  "public/welcome.html" : $('header li').eq(4).addClass("active");break;
        default:return;
      }
    }
  }
  
  module.exports = indexController
  

/***/ }),
/* 2 */
/***/ (function(module, exports) {

module.exports = "<header>    <div class=\"headerinner clear\">        <div class=\"logo_header\"></div>        <ul class=\"nav_header clear\">            <li>首页</li>            <li>点餐</li>            <li>订单</li>            <!-- <li>换桌</li> -->            <li>登录</li>            <li>欢迎</li>        </ul>    </div></header>"

/***/ }),
/* 3 */,
/* 4 */,
/* 5 */,
/* 6 */,
/* 7 */,
/* 8 */,
/* 9 */,
/* 10 */,
/* 11 */,
/* 12 */,
/* 13 */,
/* 14 */,
/* 15 */,
/* 16 */,
/* 17 */
/***/ (function(module, exports, __webpack_require__) {

const indexTpl = __webpack_require__(0)
const headerTpl = __webpack_require__(2)
const homeOrderTpl = __webpack_require__(18)


const indexController = __webpack_require__(1)
const orderController = __webpack_require__(19)

document.getElementById('root').innerHTML = indexTpl
document.querySelector('.container').innerHTML = headerTpl + homeOrderTpl

indexController.headerAction()
orderController.renderFirstLevel()
orderController.addToCartButtonEvent()

/***/ }),
/* 18 */
/***/ (function(module, exports) {

module.exports = "<div class=\"orderMainOut\">    <div class=\"orderMain clear\">        <div class=\"orderList\">            <ul class=\"clear\" id=\"listul\">                <!-- <li class=\"fir_li\" >                    <a>咖啡</a>                    <ul class=\"clear\">                        <li class=\"sec_li\">二级菜单</li>                        <li class=\"sec_li\">二级菜单</li>                        <li class=\"sec_li\">二级菜单</li>                    </ul>                </li>                 -->            </ul>        </div>        <div class=\"orderContainer\">            <ul class=\"clear\">                <!-- <li class=\"goodInfo\">                    <div class=\"goodImage\"></div>                    <div class=\"goodName\">咖啡咖啡给飞机打</div>                    <div class=\"goodPrice\">120元</div>                    <button>立即购买</button>                </li> -->            </ul>        </div>    </div></div>"

/***/ }),
/* 19 */
/***/ (function(module, exports, __webpack_require__) {

const orderModel = __webpack_require__(20)

module.exports = {
    // 渲染一级菜单
    renderFirstLevel() {
        orderModel.getFirstLevel()
            .then(resultFir => {
                this.jsonFir = resultFir.res;
                var firstLevelLi = "";
                this.jsonFir.forEach(function (itemFir, index) {
                    firstLevelLi += `
                <li class="fir_li">
                    <a>${itemFir}</a>
                    <ul class="clear secul">
                    </ul>
                </li>
                `
                })
                $("#listul").html(firstLevelLi)
                $(".fir_li>a").on("click", function () {
                    $(this).next().slideToggle()
                })
                this.renderSecondLevel({ type: 0 })
                this.renderSecondLevel({ type: 1 })
                this.renderSecondLevel({ type: 2 })
            })
    },

    // 渲染二级菜单
    renderSecondLevel(data) {
        orderModel.getSecondLevel(data)
            .then(resultSec => {
                var jsonSec = resultSec.res;
                var secondLevelLi = "";
                for (var i = 0; i < jsonSec.length; i++) {
                    secondLevelLi += `
                <li class="sec_li" data-type=${data.type}>${jsonSec[i]}</li>
                `
                }
                $('#listul > li').eq(`${data.type}`).find('ul').html(secondLevelLi)
                this.menuDelegation(data.type)
            })
    },

    // 二级菜单按钮事件
    menuDelegation(data) {
        var that = this
        $(`.sec_li[data-type=${data}]`).on('click', function (event) {
            let index = $(this).attr('data-type')
            let type = $(this).index()
            switch (index) {
                case "0":
                    orderModel.getDrinks({ type })
                        .then(data => that.renderProducts(data.data))
                    break;
                case "1":
                    orderModel.getGourmet({ type })
                        .then(data => that.renderProducts(data.data))
                    break;
                case "2":
                    orderModel.getPeripheral({ type })
                        .then(data => that.renderProducts(data.data))
                    break;
                default:
                    break;
            }
        })
        $('.sec_li:first').trigger('click')
    },

    // 渲染商品页
    renderProducts(data) {
        var html = ""
        for (var i = 0; i < data.length; i++) {
            html += `
                <li class="goodInfo">
                    <div class="goodImage"><img src="${data[i].goodImg}"></div>
                    <div class="goodName">${data[i].name}</div>
                    <div class="goodPrice">${data[i].price}元</div>
                    <div class="goodPriceVip">会员价:${data[i].membershipPrice}元</div>
                    <button data-id="${data[i].id}">加入购物车</button>
                </li> 
                `
        }
        $(".orderContainer > ul").html(html)
    },

    addToCartButtonEvent() {
        $(".orderContainer > ul").on('click', function (event) {
            if (event.target.nodeName == 'BUTTON') {
                let wsCache = new WebStorageCache()
                let wsCartList = wsCache.get('cartList')
                let goodId = event.target.getAttribute('data-id')

                if (wsCartList) { // 如果cartList存在
                    let cartList = JSON.parse(wsCartList)
                    let hasOrdered = false
                    // 遍历缓存，是否为已点商品
                    cartList.forEach(function (item, index) {
                        if (item.goodId == goodId) {
                            hasOrdered = true
                            item.goodNum += 1
                        }
                    })
                    // 如果没有点过
                    if (!hasOrdered) {
                        let goodNum = 1
                        cartList.push({ goodId, goodNum })
                    }
                    wsCache.set('cartList', JSON.stringify(cartList));
                } else { // 如果不存在cartList则新建
                    let arr = []
                    let goodNum = 1
                    arr.push({ goodId, goodNum })
                    wsCache.set('cartList', JSON.stringify(arr))
                }
            }
        })
    }
}

/***/ }),
/* 20 */
/***/ (function(module, exports) {

module.exports = {
    getFirstLevel() {
        return $.ajax({
            url: '/api/menu/firstlevel',
            type: "get",
            success: result => result
        })
    },
    getSecondLevel(data) {
        return $.ajax({
            url: '/api/menu/secondlevel',
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: result => result
        })
    },
    getDrinks(data) {
        return $.ajax({
            url: '/api/prod/drinks',
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: result => result
        })
    },
    getGourmet(data) {
        return $.ajax({
            url: '/api/prod/gourmet',
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: result => result
        })
    },
    getPeripheral(data) {
        return $.ajax({
            url: '/api/prod/peripheral',
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: result => result
        })
    },
}

/***/ })
/******/ ]);