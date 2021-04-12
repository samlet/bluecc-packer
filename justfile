build:
    ./gradlew installDist
    cp build/libs/bluecc-packer.jar {{env_var("HOME")}}/ofbiz/plugins/cc-pay/lib/

rebuild:
    ./gradlew clean installDist
    ls -alh build/libs/bluecc-packer.jar
    cp build/libs/bluecc-packer.jar {{env_var("HOME")}}/ofbiz/plugins/cc-pay/lib/

hello:
    ./build/install/bluecc-packer/bin/hello-world-server

