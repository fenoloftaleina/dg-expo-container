(ns dg-native-container.core
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :refer [subscribe dispatch dispatch-sync]]
              [oops.core :refer [ocall]]
              [dg-native-container.handlers]
              [dg-native-container.subs]))

(def ReactNative (js/require "react-native"))
(def expo (js/require "expo"))
(def AtExpo (js/require "@expo/vector-icons"))
(def ionicons (.-Ionicons AtExpo))
(def ic (r/adapt-react-class ionicons))

(def webview (r/adapt-react-class (.-WebView ReactNative)))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [webview {:source {:uri "https://fenoloftaleina.github.io/dg/resources/public"}
                :style {:flex 1}}])))

(defn init []
  (dispatch-sync [:initialize-db])
  (ocall expo "registerRootComponent" (r/reactify-component app-root)))
