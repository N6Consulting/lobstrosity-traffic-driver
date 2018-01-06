(defproject com.n6consulting/lobstrosity-traffic-driver "0.1.0-SNAPSHOT"
  :description      "Example application and simulation"
  :dependencies   [[org.clojure/clojure "1.9.0"]
                   [com.datomic/datomic-free         "0.9.5656"  :exclusions [org.clojure/clojure joda-time]]
                   [com.datomic/simulant             "0.1.8"]
                   [org.clojure/data.generators      "0.1.2"]
                   [org.clojure/tools.cli            "0.3.5"]
                   [org.craigandera/causatum         "0.3.0"]
                   [org.apache.commons/commons-math3 "3.6.1"]
                   [clj-http                         "3.7.0"]]

  :license      {:name         "Eclipse Public License - v 1.0"
                 :url          "http://www.eclipse.org/legal/epl-v10.html"
                 :distribution :repo
                 :comments     "same as Clojure"}

  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"}}

  :main         simtest.cli

  :jvm-opts     ["-Xmx2g" "-Xms2g" "-server" "-Ddatomic.objectCacheMax=128m"
                 "-Ddatomic.memoryIndexMax=256m" "-Ddatomic.memoryIndexThreshold=32m"])
