if [ -e go/bin/gotty ]
then
    echo "GoTTY already installed."
else
    go get github.com/yudai/gotty
fi
apt install x11-apps -y
go/bin/gotty -w java -jar memory-match.jar
