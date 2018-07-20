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
/******/ 	return __webpack_require__(__webpack_require__.s = 12);
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
/* 3 */
/***/ (function(module, exports, __webpack_require__) {

const loginModel = __webpack_require__(4)

const login = function () {
    var oEmall = $("#emall").val();
    var opwd = $("#password").val();
    var emall_true;
    var phonereg = /^[1][3,4,5,7,8][0-9]{9}$/;
    var regEmall = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
    var ologin_btn = document.getElementById("login_btn");
    // 1.@之前必须有内容且只能是字母（大小写）、数字、下划线(_)、减号（-）、点（.）
    // 2.@和最后一个点（.）之间必须有内容且只能是字母（大小写）、数字、点（.）、减号（-），且两个点不能挨着
    // 3.最后一个点（.）之后必须有内容且内容只能是字母（大小写）、数字且长度为大于等于2个字节，小于等于6个字节
    $("#emall").on("blur", function () {
        var oEmall = $("#emall").val();
        if (!regEmall.test(oEmall)) {
            $(".errMsg").css("opacity", 1)
            emall_true = false
        } else {
            $(".errMsg").css("opacity", 0)
            emall_true = true
        }
    })

    //登陆的逻辑登陆的逻辑登陆的逻辑登陆的逻辑登陆的逻辑登陆的逻辑登陆的逻辑
    $("#login_btn").on("click", async function () {
        let { username, password } = {
            username: $("#emall").val(),
            password: $("#password").val()
        }
        if (username && password && emall_true) {
            let userinfo = { username, password }
            let result = await loginModel.login(userinfo)
            if (result.res) {
                // create WebStorageCache instance.
                var wsCache = new WebStorageCache();
                wsCache.set('haslogin', true);
                var d = dialog({
                    content: '登录成功！即将为您跳转至点餐页面~(＾－＾)'
                });
                d.showModal();
                // 登录成功后自动跳转页面至点餐页
                setTimeout(function () {
                    d.close().remove();
                    var url = '/public/order.html'
                    location.href = url
                }, 2000);
            } else {
                var d = dialog({
                    content: '请输入有效的邮箱和密码！'
                });
                d.showModal();
                setTimeout(function () {
                    d.close().remove();
                }, 2000);
            }
        } else {
            var d = dialog({
                content: '请输入有效的邮箱和密码！'
            });
            d.showModal();
            setTimeout(function () {
                d.close().remove();
            }, 2000);
        }
    })
}

module.exports = {
    login
}

/***/ }),
/* 4 */
/***/ (function(module, exports) {

const login = (data) => {
  return $.ajax({
    url: '/api/users/login',
    type: 'POST',
    contentType: "application/json; charset=utf-8",
    data: JSON.stringify(data),
    success: result => result
  })
}

module.exports = { login }

/***/ }),
/* 5 */,
/* 6 */,
/* 7 */,
/* 8 */,
/* 9 */,
/* 10 */,
/* 11 */,
/* 12 */
/***/ (function(module, exports, __webpack_require__) {

const indexTpl = __webpack_require__(0)
const headerTpl = __webpack_require__(2)
const homeLoginTpl = __webpack_require__(13)

const indexController = __webpack_require__(1)
const loginController = __webpack_require__(3)


document.getElementById('root').innerHTML = indexTpl
document.querySelector('.container').innerHTML = headerTpl + homeLoginTpl

indexController.headerAction()
loginController.login()

/***/ }),
/* 13 */
/***/ (function(module, exports) {

module.exports = "<div class=\"space\"></div><div class=\"login_main\">    <section class=\"clear\">        <div class=\"login_bg\"></div>        <div class=\"login_context\">            <div class=\"tip\">登录缘来，享受更多优惠</div>            <form action=\"\">                <input type=\"text\" name=\"log_username\" id=\"emall\" placeholder=\"邮箱\">                <span class=\"errMsg\">邮箱格式输入错误</span>                <input type=\"password\" name=\"log_pas\" id=\"password\"placeholder=\"密码\">                <div  id=\"login_btn\" class=\"aa\">登录</div>                                                  </form>        </div>    </section></div>"

/***/ })
/******/ ]);