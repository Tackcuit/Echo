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
/******/ 	return __webpack_require__(__webpack_require__.s = 8);
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
/* 8 */
/***/ (function(module, exports, __webpack_require__) {

const indexTpl = __webpack_require__(0)
const headerTpl = __webpack_require__(2)
const homeTpl = __webpack_require__(9)

const indexController = __webpack_require__(1)
const homeController = __webpack_require__(10)

document.getElementById('root').innerHTML = indexTpl
document.querySelector('.container').innerHTML = headerTpl + homeTpl

indexController.headerAction()
homeController.renderNewest()
homeController.renderHottest()
homeController.nextPic()

var mySwiper = new Swiper('.swiper-container', {
  direction: 'horizontal',
  loop: true,
  // 如果需要分页器
  pagination: { 
    el: '.swiper-pagination',
    paginationClickable: true,
  },
  // centeredSlides: true,
  autoplay: 1000,
  // autoplayDisableOnInteraction: false,
  // 如果需要前进后退按钮
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  }
})   

/***/ }),
/* 9 */
/***/ (function(module, exports) {

module.exports = "<div class =\"banner-container\">    <!-- <div class = \"side-nav\">        <ul class = \"side-nav-list\">            <li>咖啡</li>            <li>甜点</li>            <li>冷饮</li>            <li class =\"side-nav-list-last\">沙拉</li>        </ul>    </div> -->        <div class = \"banner clear\">            <!-- <div class = \"banner-pic\"> -->                <!-- <ul class = \"banner-container\"> -->                    <!-- <li class = \"banner-pic-list\"> -->                        <!-- <img src = \"./static/images/banner-frostino.jpg\"/> -->                    <!-- </li> -->                <!-- </ul> -->            <!-- </div> -->            <!-- <div id = \"home-banner-list\">                <ul class = \"banner-list\">                    <li class =\"active\"></li>                    <li></li>                    <li></li>                    <li></li>                </ul>                <ul class = \"navigator\">                    <li class = \"right\">                        &gt;                    </li>                    <li class = \"left\">                        &lt;                    </li>                </ul>            </div> -->        </div>        <div class=\"swiper-container\">            <div class=\"swiper-wrapper\">                <div class=\"swiper-slide\">                    <img src = \"./static/images/banner-frostino.jpg\"/>                </div>                <div class=\"swiper-slide\">                    <img src = \"./static/images/bannerbp2.jpg\"/>                </div>                <div class=\"swiper-slide\">                    <img src = \"./static/images/bannerbp3.jpg\"/>                </div>                <div class=\"swiper-slide\">                        <img src = \"./static/images/bannerbp4.jpg\"/>                </div>        </div>                         <div class=\"swiper-pagination\"></div>                                        <div class=\"swiper-button-prev swiper-button-black\"></div>            <div class=\"swiper-button-next swiper-button-black\"></div>        </div>        <div class = \"margin-between\">            <div class =\"margin-between-bottom\">                <h2>                    本店热卖                </h2>            </div>        </div>        <div class = \"hot-single\">            <ul class =\"hot-single-list clear\">                <!-- <li>                    <img src =\"./static/images/hot1.jpg\"/>                </li>                <li>                    <img src =\"./static/images/hot2.jpeg\"/>                </li>                <li>                    <img src =\"./static/images/hot5.jpeg\"/>                </li>                <li class = \"hot-single-list-last\">                    <img src =\"./static/images/hot4.jpeg\"/> -->                </li>            </ul>            </div>        <div class = \"margin-between\">            <div class =\"margin-between-bottom\">                <h2>                    本店新品                </h2>            </div>        </div>        <div class = \"hot-single\">            <ul class =\"hot-single-list clear\">                            </ul>        </div>    </div>    <footer>        <div class=\"footerinner\">            <div class=\"logo_footer\"></div>            <article>                <div class=\"contact\">联系我们</div>                <p class=\"tel\">Tel: 010-28855688</p>                <p class=\"emall\">邮箱: contact@yuanlai.com</p>            </article>        </div>    </footer>"

/***/ }),
/* 10 */
/***/ (function(module, exports, __webpack_require__) {

const homeModel = __webpack_require__(11)

const homeController = {
    renderNewest() {
        homeModel.getNewest()
            .then(result => {
                var jsonNew = result.data.drinks;
                var html = '';
                for (let i = 0; i < jsonNew.length; i++) {
                    console.log(jsonNew[i].goodImg);
                    html += `
                            <li>
                                <img src="${jsonNew[i].goodImg}">
                                <p>${jsonNew[i].name}</p>
                            </li>
                            `
                }
                $(".hot-single-list").eq(0).html(html)
            })
    },
    renderHottest() {
        homeModel.getHottest({ type: 0 })
            .then(result => {
                var jsonHot = result.data;
                var html = '';
                for (let i = 0; i < jsonHot.length; i++) {
                    html += `
                <li>
                <img src="${jsonHot[i].goodImg}">
                <p>${jsonHot[i].name}</p>
                </li>
                `
                }
                $(".hot-single-list").eq(1).html(html)

            })
    },
    nextPic() {
        var t;
        setTimeout(function () {
            t = setInterval(function () {
                $(".swiper-button-next").click()
            }, 3000)
        }, 2000)
        $(".swiper-container").on("mousemove", function () {
            clearInterval(t)
        })
        $(".swiper-container").on("mouseleave", function () {
            t = setInterval(function () {
                $(".swiper-button-next").click()
            }, 3000)
        })
    }
}
module.exports = homeController

/***/ }),
/* 11 */
/***/ (function(module, exports) {

module.exports = {
    getNewest() {
        return $.ajax({
            url: '/api/prod/newest',
            type: "get",
            success: result => result
        })
    },
    getHottest(data) {
        return $.ajax({
            url: '/api/prod/hottest',
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: result => result
        })
    }
}

/***/ })
/******/ ]);