if [ -e go/bin/gotty ]
then
    echo "GoTTY already installed."
else
    go get github.com/yudai/gotty
fi
apt install x11-apps -y
apt-get install xorg openbox
export DISPLAY=:0
setenv DISPLAY :0
go/bin/gotty -w java -jar memory-match.jar
