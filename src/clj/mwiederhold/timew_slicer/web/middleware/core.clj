(ns mwiederhold.timew-slicer.web.middleware.core
  (:require
    [mwiederhold.timew-slicer.env :as env]
    [ring.middleware.defaults :as defaults]
    [ring.middleware.session.cookie :as cookie] ))

(defn wrap-base
  [{:keys [metrics site-defaults-config cookie-session] :as opts}]
  (fn [handler]
    (cond-> ((:middleware env/defaults) handler opts)
            true (defaults/wrap-defaults
                   (assoc-in site-defaults-config [:session :store] (cookie/cookie-store cookie-session)))
            )))
