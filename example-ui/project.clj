(defproject com.datomic/simulant-example-ui "0.1.0-SNAPSHOT"
  :description "Demo UI for a simulation test"

  :source-paths ["src/clj" "src/cljs"]

  :dependencies [[org.clojure/clojure            _]
                 [org.clojure/clojurescript      _ :scope "provided"]
                 [io.pedestal/pedestal.service   _]
                 [io.pedestal/pedestal.jetty     _]
                 [ch.qos.logback/logback-classic _ :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j         _]
                 [org.slf4j/jcl-over-slf4j       _]
                 [org.slf4j/log4j-over-slf4j     _]

                 [ring                           _]
                 [compojure                      "1.3.4"]
                 [enlive                         _]
                 [org.omcljs/om                  _]
                 [environ                        "1.0.0"]
                 [com.cognitect/transit-cljs     _]
                 [com.cognitect/transit-clj      _]]

  :plugins [[lein-modules "0.3.9"]
            [lein-cljsbuild "1.0.6"]
            [lein-environ "1.0.0"]]

  :min-lein-version "2.5.0"

  :uberjar-name "simulant-example-ui.jar"

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to     "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/out"
                                        :source-map    "resources/public/js/out.js.map"
                                        :preamble      ["react/react.min.js"]
                                        :externs       ["react/externs/react.js"]
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :profiles {:dev {:repl-options {:init-ns example-ui.server
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :dependencies [[org.clojure/tools.nrepl            "0.2.10"]
                                  [figwheel                           _]
                                  [com.cemerick/piggieback            "0.2.0"]
                                  [weasel                             _]
                                  [io.pedestal/pedestal.service-tools _]
                                  [leiningen                          "2.5.1"]]

                   :plugins [[lein-figwheel "0.3.3"]]

                   :figwheel {:http-server-root "public"
                              :port 3449
                              :css-dirs ["resources/public/css"]}

                   :env {:is-dev true}

                   :cljsbuild {:builds {:app {:source-paths ["env/dev/cljs"]}}}}

             :uberjar {:hooks [leiningen.cljsbuild]
                       :env {:production true}
                       :omit-source true
                       :aot :all
                       :cljsbuild {:builds {:app
                                            {:source-paths ["env/prod/cljs"]
                                             :compiler
                                             {:optimizations :advanced
                                              :pretty-print false}}}}}})
