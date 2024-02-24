if [ -e go/bin/gotty ]
then
    echo "GoTTY already installed."
else
    go get github.com/yudai/gotty
fi
export DISPLAY=:0
go/bin/gotty -w java -jar memory-match.jar
