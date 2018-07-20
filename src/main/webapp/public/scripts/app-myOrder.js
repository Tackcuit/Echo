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
/******/ 	return __webpack_require__(__webpack_require__.s = 21);
/******/ })
/************************************************************************/
/******/ ({

/***/ 0:
/***/ (function(module, exports) {

module.exports = "<div class=\"container\"></div>"

/***/ }),

/***/ 1:
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

/***/ 2:
/***/ (function(module, exports) {

module.exports = "<header>    <div class=\"headerinner clear\">        <div class=\"logo_header\"></div>        <ul class=\"nav_header clear\">            <li>首页</li>            <li>点餐</li>            <li>订单</li>            <!-- <li>换桌</li> -->            <li>登录</li>            <li>欢迎</li>        </ul>    </div></header>"

/***/ }),

/***/ 21:
/***/ (function(module, exports, __webpack_require__) {

const indexTpl = __webpack_require__(0)
const headerTpl = __webpack_require__(2)
const homeMyOrderTpl = __webpack_require__(22)

const indexController = __webpack_require__(1)
const myOrderController = __webpack_require__(23)

document.getElementById('root').innerHTML = indexTpl
document.querySelector('.container').innerHTML = headerTpl + homeMyOrderTpl

indexController.headerAction()
myOrderController.renderOrderList()
myOrderController.submitOrder()

/***/ }),

/***/ 22:
/***/ (function(module, exports) {

module.exports = "<div class=\"myOrderOut\">    <div class=\"myOrder\">        <table class=\"orderTable\" cellspacing=\"0\">        </table>        <div class=\"priceInfo\">            <span class=\"priceFont\">合计：</span>            <span class=\"price\">0</span>            <a class=\"subOrder\">提交订单</a>        </div>    </div></div>"

/***/ }),

/***/ 23:
/***/ (function(module, exports, __webpack_require__) {

const myOrderModel = __webpack_require__(24)
const wsCache = new WebStorageCache()

async function renderOrderList() {
  let wsCartList = wsCache.get('cartList')
  let html = `<tr>
                <th>产品图片</th>
                <th>产品名称</th>
                <th>价格</th>
                <th>数量</th>
            </tr>`
  if (wsCartList) {
    let cartList = JSON.parse(wsCartList)
    let goodsIdList = []
    cartList.forEach((item, index) => {
      goodsIdList.push(item.goodId)
    })
    let result = await myOrderModel.getGoodsDetail({ "goods": goodsIdList })
    let userLogined = wsCache.get('haslogin')
    result.details.forEach((item, index) => {
      if (userLogined) {
        html += `
                  <tr>
                      <td><div class="orderImg"><img src="${item.goodImg}"></div></td>
                      <td>${item.goodName}</td>
                      <td>${item.membershipPrice}</td>
                      <td>
                          <button data-id="${item.id}" class="decrease">-</button>
                          <span class="prodNum">${cartList[index].goodNum}</span>
                          <button data-id="${item.id}" class="increase">+</button>
                      </td>
                  </tr>
                  `
      } else {
        html += `
                <tr>
                    <td><div class="orderImg"><img src="${item.goodImg}"></div></td>
                    <td>${item.goodName}</td>
                    <td>${item.price}</td>
                    <td>
                        <button data-id="${item.id}" class="decrease">-</button>
                        <span class="prodNum">${cartList[index].goodNum}</span>
                        <button data-id="${item.id}" class="increase">+</button>
                    </td>
                </tr>
                `
      }
    })
    $('.orderTable').html(html)
  } else {
    html = `
            <tr>
                <th>产品图片</th>
                <th>产品名称</th>
                <th>价格</th>
                <th>数量</th>
            </tr>
            <tr>
              <td colspan="4">暂无商品被添加至购物车</td>
            </tr>
    `
    $('.orderTable').html(html)
  }
  this.increaseBtnEvent()
  this.decreaseBtnEvent()
  this.calculateTotalPrice()
}

async function calculateTotalPrice() {
  let wsCartList = wsCache.get('cartList')
  let totalPrice = 0
  if (wsCartList) {
    let cartList = JSON.parse(wsCartList)
    let goodsIdList = []
    cartList.forEach((item, index) => {
      goodsIdList.push(item.goodId)
    })
    let result = await myOrderModel.getGoodsDetail({ "goods": goodsIdList })
    let userLogined = wsCache.get('haslogin')
    result.details.forEach((item, index) => {
      if (userLogined) { // 会员价
        totalPrice += item.membershipPrice * cartList[index].goodNum
      } else { // 非会员价
        totalPrice += item.price * cartList[index].goodNum
      }
    })
  }
  $('.price').html(totalPrice)
}



function submitOrder() {
  $('.subOrder').on('click', async function () {
    let userLogined = wsCache.get('haslogin')
    let wsCartList = wsCache.get('cartList')
    let cartList = JSON.parse(wsCartList)
    let tableId = Math.ceil(Math.random() * 100)
    userLogined = userLogined ? 1 : 0
    let result = await myOrderModel.addToCart({ userLogined, tableId, cartList })
    if (result.res) {
      var d = dialog({
        content: '提交成功！请您耐心等待~(＾－＾)'
      });
      d.showModal();
      // 登录成功后自动跳转页面至点餐页
      setTimeout(function () {
        d.close().remove();
      }, 2000);
    } else {
      var d = dialog({
        content: '提交出错！'
      });
      d.showModal();
      // 登录成功后自动跳转页面至点餐页
      setTimeout(function () {
        d.close().remove();
      }, 2000);
    }
  })
}

function increaseBtnEvent() {
  let that = this
  $('.increase').on('click', function () {
    let goodId = $(this).attr('data-id')
    let wsCache = new WebStorageCache()
    let wsCartList = wsCache.get('cartList')
    let cartList = JSON.parse(wsCartList)
    cartList.forEach(function (item, index) {
      if (item.goodId == goodId) {
        item.goodNum += 1
        $(this).siblings('.prodNum').html(item.goodNum)
      }
    }.bind(this))
    wsCache.set('cartList', JSON.stringify(cartList));
    that.calculateTotalPrice()
  })
}

function decreaseBtnEvent() {
  let that = this
  $('.decrease').on('click', function () {
    let goodId = $(this).attr('data-id')
    let wsCache = new WebStorageCache()
    let wsCartList = wsCache.get('cartList')
    let cartList = JSON.parse(wsCartList)
    cartList.forEach(function (item, index) {
      if (item.goodId == goodId) {
        if (item.goodNum) {
          item.goodNum -= 1
          $(this).siblings('.prodNum').html(item.goodNum)
        }
      }
    }.bind(this))
    wsCache.set('cartList', JSON.stringify(cartList));
    that.calculateTotalPrice()
  })
}

module.exports = { renderOrderList, submitOrder, increaseBtnEvent, decreaseBtnEvent, calculateTotalPrice }

/***/ }),

/***/ 24:
/***/ (function(module, exports) {

const getGoodsDetail = function (data) {
  return $.ajax({
    url: '/api/service/goodDetail',
    type: 'POST',
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: result => result
  })
}

const addToCart = function (data) {
  return $.ajax({
    url: '/api/service/addToCart',
    type: 'POST',
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: result => result
  })
}

module.exports = { getGoodsDetail, addToCart }

/***/ })

/******/ });