package nl.scouting.hit.sitecreator.input.module.soap;

public class SolSoapClient {

	/** Scouts OnLine Unique Identifier. */
	private final String sui;

	/** SOL session identifier of the logged in user. */
	private String sessionId;

	/** SOL session name. */
	private String sessionName;

	/**
	 * The PRIVATE KEY. The private key needs to be supported when an instance
	 * of the class is constructed.
	 */
	private final String privateKey;
	/**
	 * A url that points to the WSDL file that will be used by the client
	 */
	private final String wsdlUrl;

	/**
	 * Constructor Creates the SoapClient
	 * 
	 * @param sui
	 *            ScoutsOnLine Unique Identifier
	 * @param privateKey
	 *            Private key of the X509 certificate
	 * @param wsdlUrl
	 *            Url to the wsdl file that has to be used.
	 */
	public SolSoapClient(final String sui, final String privateKey,
			final String wsdlUrl) {
		// super(wsdlUrl, SOAP_1_2);
		this.sui = sui;
		this.privateKey = privateKey;
		this.wsdlUrl = wsdlUrl;
	}
}
