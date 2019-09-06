import groovy.json.JsonSlurper

//AUTHENTICATION STAGING
def authPath = '/home/xndev/opsroot/credentials/'
def cwAuthFile = authPath + 'cw.txt'
def cwClientIdFile = authPath + 'cw-clientid.txt'
def cwAuthText = new File(cwAuthFile).getText().trim()
def cwClientId = new File(cwClientIdFile).getText().trim()

// GET EXAMPLE
def cwExampleGet = new URL("https://api-na.myconnectwise.net/v4_6_release/apis/3.0/service/tickets").openConnection() as HttpURLConnection
cwExampleGet.setRequestProperty( 'Authorization', cwAuthText )
cwExampleGet.setRequestProperty( 'Accept', 'application/json' )
cwExampleGet.setRequestProperty( 'clientId', cwClientId )

def getRC = cwExampleGet.getResponseCode()
log.info(getRC)
if(getRC.equals(200)) {
    log.info(cwExampleGet.getInputStream().getText())
}
else {
	log.info("Code was - " + getRC)
	try {
		log.info(cwExampleGet.getInputStream().getText())
	}
	catch(connEx) {
		log.info("Failed to rec'v stream")
	}
}
