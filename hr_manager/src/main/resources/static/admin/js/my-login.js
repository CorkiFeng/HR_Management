$(function() {
	$("input[type='password'][data-eye]").each(function(i) {
		let $this = $(this);

		$this.wrap($("<div/>", {
			style: 'position:relative'
		}));
		$this.css({
			paddingRight: 60
		});
		$this.after($("<div/>", {
			html: 'Show',
			class: 'btn btn-primary btn-sm',
			id: 'passeye-toggle-'+i,
			style: 'position:absolute;right:10px;top:50%;transform:translate(0,-50%);padding: 2px 7px;font-size:12px;cursor:pointer;'
		}));
		$this.after($("<input/>", {
			type: 'hidden',
			id: 'passeye-' + i
		}));
		$this.on("keyup paste", function() {
			$("#passeye-"+i).val($(this).val());
		});
		$("#passeye-toggle-"+i).on("click", function() {
			if($this.hasClass("show")) {
				$this.attr('type', 'password');
				$this.removeClass("show");
				$(this).removeClass("btn-outline-primary");
			}else{
				$this.attr('type', 'text');
				$this.val($("#passeye-"+i).val());
				$this.addClass("show");
				$(this).addClass("btn-outline-primary");
			}
		});
	});
});

//产生验证码
window.onload = function() {
	createCode();
	var timeDiv = document.getElementById("time");
	window.setInterval(function(){
		timeDiv["innerHTML"] = new Date().toLocaleString();
	}, 1000);
};
var code; //在全局定义验证码
function createCode() {
	code = "";
	var codeLength = 4; //验证码的长度
	var checkCode = document.getElementById("code");
	var random = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
		'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']; //随机数
	for(var i = 0; i < codeLength; i++) { //循环操作
		var index = Math.floor(Math.random() * 36); //取得随机数的索引（0~35）
		code += random[index]; //根据索引取得随机数加到code上
	}
	checkCode.value = code; //把code值赋给验证码
}

function check(){
	var inputCode = document.getElementById("ctl00_txtcode").value.toUpperCase();
	var id = $("#id").val();
	var password = $("#password").val();
	if(id==="" || password===""||inputCode===""){
		alert("输入信息不能为空,请完善信息！");
		return false;
	}else if(inputCode!==code){
		alert("验证码输入错误,请重新输入！");
		createCode(); //刷新验证码
		document.getElementById("ctl00_txtcode").value = ""; //清空文本框
		return false;
	}
	return true;
}

function change() {
	createCode();
	var timeDiv = document.getElementById("time");
	window.setInterval(function(){
		timeDiv["innerHTML"] = new Date().toLocaleString();
	}, 1000);
}



!(function(window, document) {
	function GVerify(options) { //创建一个图形验证码对象，接收options对象为参数
		this.options = { //默认options参数值
			id: "", //容器Id
			canvasId: "verifyCanvas", //canvas的ID
			width: "100", //默认canvas宽度
			height: "30", //默认canvas高度
			type: "blend", //图形验证码默认类型blend:数字字母混合类型、number:纯数字、letter:纯字母
			code: ""
		}

		if(Object.prototype.toString.call(options) === "[object Object]"){//判断传入参数类型
			for(var i in options) { //根据传入的参数，修改默认参数值
				this.options[i] = options[i];
			}
		}else{
			this.options.id = options;
		}

		this.options.numArr = "0,1,2,3,4,5,6,7,8,9".split(",");
		this.options.letterArr = getAllLetter();

		this._init();
		this.refresh();
	}

	GVerify.prototype = {
		/**版本号**/
		version: '1.0.0',

		/**初始化方法**/
		_init: function() {
			var con = document.getElementById(this.options.id);
			var canvas = document.createElement("canvas");
			this.options.width = con.offsetWidth > 0 ? con.offsetWidth : "100";
			this.options.height = con.offsetHeight > 0 ? con.offsetHeight : "30";
			canvas.id = this.options.canvasId;
			canvas.width = this.options.width;
			canvas.height = this.options.height;
			canvas.style.cursor = "pointer";
			canvas.innerHTML = "您的浏览器版本不支持canvas";
			con.appendChild(canvas);
			var parent = this;
			canvas.onclick = function(){
				parent.refresh();
			}
		},

		/**生成验证码**/
		refresh: function() {
			this.options.code = "";
			var canvas = document.getElementById(this.options.canvasId);
			if(canvas.getContext) {
				var ctx = canvas.getContext('2d');
			}else{
				return;
			}

			ctx.textBaseline = "middle";

			ctx.fillStyle = randomColor(180, 240);
			ctx.fillRect(0, 0, this.options.width, this.options.height);

			if(this.options.type === "blend") { //判断验证码类型
				var txtArr = this.options.numArr.concat(this.options.letterArr);
			} else if(this.options.type === "number") {
				var txtArr = this.options.numArr;
			} else {
				var txtArr = this.options.letterArr;
			}

			for(var i = 1; i <= 4; i++) {
				var txt = txtArr[randomNum(0, txtArr.length)];
				this.options.code += txt;
				ctx.font = randomNum(this.options.height/2, this.options.height) + 'px SimHei'; //随机生成字体大小
				ctx.fillStyle = randomColor(50, 160); //随机生成字体颜色
				ctx.shadowOffsetX = randomNum(-3, 3);
				ctx.shadowOffsetY = randomNum(-3, 3);
				ctx.shadowBlur = randomNum(-3, 3);
				ctx.shadowColor = "rgba(0, 0, 0, 0.3)";
				var x = this.options.width / 5 * i;
				var y = this.options.height / 2;
				var deg = randomNum(-30, 30);
				/**设置旋转角度和坐标原点**/
				ctx.translate(x, y);
				ctx.rotate(deg * Math.PI / 180);
				ctx.fillText(txt, 0, 0);
				/**恢复旋转角度和坐标原点**/
				ctx.rotate(-deg * Math.PI / 180);
				ctx.translate(-x, -y);
			}
			/**绘制干扰线**/
			// for(var i = 0; i < 4; i++) {
			// 	ctx.strokeStyle = randomColor(40, 180);
			// 	ctx.beginPath();
			// 	ctx.moveTo(randomNum(0, this.options.width), randomNum(0, this.options.height));
			// 	ctx.lineTo(randomNum(0, this.options.width), randomNum(0, this.options.height));
			// 	ctx.stroke();
			// }
			/**绘制干扰点**/
			// for(var i = 0; i < this.options.width/4; i++) {
			// 	ctx.fillStyle = randomColor(0, 255);
			// 	ctx.beginPath();
			// 	ctx.arc(randomNum(0, this.options.width), randomNum(0, this.options.height), 1, 0, 2 * Math.PI);
			// 	ctx.fill();
			// }
		},

		/**验证验证码**/
		validate: function(code){
			var code = code.toLowerCase();
			var v_code = this.options.code.toLowerCase();
			console.log(v_code);
			if(code === v_code){
				return true;
			}else{
				this.refresh();
				return false;
			}
		}
	}
	/**生成字母数组**/
	function getAllLetter() {
		var letterStr = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		return letterStr.split(",");
	}
	/**生成一个随机数**/
	function randomNum(min, max) {
		return Math.floor(Math.random() * (max - min) + min);
	}
	/**生成一个随机色**/
	function randomColor(min, max) {
		var r = randomNum(min, max);
		var g = randomNum(min, max);
		var b = randomNum(min, max);
		return "rgb(" + r + "," + g + "," + b + ")";
	}
	window.GVerify = GVerify;
})(window, document);
