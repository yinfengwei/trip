;(function(window) {

  var svgSprite = '<svg>' +
    '' +
    '<symbol id="icon-xiangqing" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M703.957333 0.938667C699.861333 0.938667 256.042667 0 256.042667 0 185.301333 0 128.042667 57.258667 128.042667 128l0 767.914667c0 70.656 57.258667 128 128 128l575.914667 0c70.656 0 128-57.258667 128-128L959.957333 256 703.957333 0.938667zM735.957333 767.914667 351.957333 767.914667c-17.664 0-32-14.336-32-32 0-17.664 14.336-32 32-32l383.914667 0c17.664 0 32 14.336 32 32C767.957333 753.578667 753.621333 767.914667 735.957333 767.914667zM735.957333 575.914667 351.957333 575.914667c-17.664 0-32-14.336-32-32 0-17.664 14.336-32 32-32l383.914667 0c17.664 0 32 14.336 32 32C767.957333 561.578667 753.621333 575.914667 735.957333 575.914667zM767.957333 256c-35.328 0-64-28.672-64-64 0 0 0-51.797333 0-128L895.957333 256 767.957333 256z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-daozheli-copy" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M154.912 437.888l72.768-72.288h523.521v145.408l269.984-222.848-269.984-222.879v145.408h-587.423l-163.776 162.784v532.031h154.912z"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-quzheli" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M489.576116 14.608104L3.856643 982.395023c-10.956078 21.912157 10.956078 47.476339 32.868235 36.520261l467.459342-273.901958c3.652026-3.652026 10.956078-3.652026 18.260131 0L986.251666 1022.56731c25.564183 7.304052 43.824313-14.608104 32.868235-36.520261L533.400429 14.608104c-7.304052-18.260131-32.868235-18.260131-43.824313 0z" fill="" ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-ic_pin_drop_px" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M768 341.333333c0-141.44-114.56-256-256-256S256 199.893333 256 341.333333c0 192 256 469.333333 256 469.333334s256-277.333333 256-469.333334z m-341.333333 0c0-47.146667 38.186667-85.333333 85.333333-85.333333s85.333333 38.186667 85.333333 85.333333-38.186667 85.333333-85.333333 85.333334-85.333333-38.186667-85.333333-85.333334zM213.333333 853.333333v85.333334h597.333334v-85.333334H213.333333z" fill="" ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-xingxing" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M702.66 291.489C573.849-68.568 446.493-68.064 320.425 292.945c-382.179 11.201-421.103 132.508-116.715 363.92-107.418 366.947-4.088 441.434 310.101 223.518 315.757 215.564 418.582 140.238 308.364-225.869C1124.715 420.805 1084.951 299.778 702.66 291.489z" fill="#F4C600" ></path>' +
    '' +
    '</symbol>' +
    '' +
    '</svg>'
  var script = function() {
    var scripts = document.getElementsByTagName('script')
    return scripts[scripts.length - 1]
  }()
  var shouldInjectCss = script.getAttribute("data-injectcss")

  /**
   * document ready
   */
  var ready = function(fn) {
    if (document.addEventListener) {
      if (~["complete", "loaded", "interactive"].indexOf(document.readyState)) {
        setTimeout(fn, 0)
      } else {
        var loadFn = function() {
          document.removeEventListener("DOMContentLoaded", loadFn, false)
          fn()
        }
        document.addEventListener("DOMContentLoaded", loadFn, false)
      }
    } else if (document.attachEvent) {
      IEContentLoaded(window, fn)
    }

    function IEContentLoaded(w, fn) {
      var d = w.document,
        done = false,
        // only fire once
        init = function() {
          if (!done) {
            done = true
            fn()
          }
        }
        // polling for no errors
      var polling = function() {
        try {
          // throws errors until after ondocumentready
          d.documentElement.doScroll('left')
        } catch (e) {
          setTimeout(polling, 50)
          return
        }
        // no errors, fire

        init()
      };

      polling()
        // trying to always fire before onload
      d.onreadystatechange = function() {
        if (d.readyState == 'complete') {
          d.onreadystatechange = null
          init()
        }
      }
    }
  }

  /**
   * Insert el before target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var before = function(el, target) {
    target.parentNode.insertBefore(el, target)
  }

  /**
   * Prepend el to target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var prepend = function(el, target) {
    if (target.firstChild) {
      before(el, target.firstChild)
    } else {
      target.appendChild(el)
    }
  }

  function appendSvg() {
    var div, svg

    div = document.createElement('div')
    div.innerHTML = svgSprite
    svgSprite = null
    svg = div.getElementsByTagName('svg')[0]
    if (svg) {
      svg.setAttribute('aria-hidden', 'true')
      svg.style.position = 'absolute'
      svg.style.width = 0
      svg.style.height = 0
      svg.style.overflow = 'hidden'
      prepend(svg, document.body)
    }
  }

  if (shouldInjectCss && !window.__iconfont__svg__cssinject__) {
    window.__iconfont__svg__cssinject__ = true
    try {
      document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>");
    } catch (e) {
      console && console.log(e)
    }
  }

  ready(appendSvg)


})(window)