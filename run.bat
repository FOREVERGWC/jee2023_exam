start gradle clean
git checkout .
git clean -f -d -e run.bat
git checkout pr/%1
git logformat -10
start gradle dbStart
start gradle bootRun