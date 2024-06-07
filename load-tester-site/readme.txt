Steps:

Setup the local server:

1. In command prompt, write 'ipconfig'
2. Get your IPv4 address and put this in the local_IP variable of app.js file
3. Run from VSCode console by 'node app.js'
4. Access from browser, through 'http://<<your_local_ip>>:3000/'
5. You may also try with localhost, but in my browser, it does not work 

Perform Load Testing in Apache-JMeter

1. Open ApacheJMeter.jar from bin folder or you may need to run the bat file.
2. Templates-Icon -> Recording -> Create -> hostToRecord = http://<<your_local_ip>>:3000
3. Go to HTTPS Test Script Recorder
4. Make sure the port matches with your proxy settings, For instance, if your proxy is 
   Address = 127.0.0.1, Port = 8181, then in Apache JMeter Script Recorder, 
   the port should be 8181
5. Make sure that the server is running...
6. Enter the site and do dummy submission
7. Click Stop, And now, You may go with the recorded steps in the Recording Controller
8. You may also add in those steps
   Assertion -> Response Assertion 
   PostProcessor -> RegularExpressionExtractor -> ([^*]+)
9. In Thread Group, you may add 
   Config Element -> CSV DataSet Config 
   Listener -> Summary Report 
10. Variables should be in ${} when used in sending parameters in request
   