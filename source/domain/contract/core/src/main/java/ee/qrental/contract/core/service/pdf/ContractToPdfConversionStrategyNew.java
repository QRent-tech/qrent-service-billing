package ee.qrental.contract.core.service.pdf;

import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@RequiredArgsConstructor
public class ContractToPdfConversionStrategyNew implements ContractToPdfConversionStrategy {
  @Override
  public boolean canApply(final ContractPdfModel contract) {
    // TODO add condition

    return false;
  }

  @Override
  public InputStream getPdfInputStream(final ContractPdfModel model) {
    return null;
  }
}
