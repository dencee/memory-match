if [ -e go/bin/gotty ]
then
    echo "GoTTY already installed."
else
    go get github.com/yudai/gotty
fi
apt install x11-apps -y
apt-get install xorg openbox
export DISPLAY=:1
setenv DISPLAY :1
go/bin/gotty -w java -jar memory-match.jar
