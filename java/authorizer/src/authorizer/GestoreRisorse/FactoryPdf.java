package authorizer.GestoreRisorse;

class FactoryPdf implements FactoryRisorsa {
	@Override
	public IRisorsa creaRisorsa() {
		return new RisorsaPdf();
	}
}