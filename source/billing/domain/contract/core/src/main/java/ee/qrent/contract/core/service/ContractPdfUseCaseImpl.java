package ee.qrent.contract.core.service;

import ee.qrent.contract.api.in.usecase.ContractPdfUseCase;
import ee.qrent.contract.api.out.ContractLoadPort;
import ee.qrent.contract.core.service.pdf.ContractToPdfConverter;
import ee.qrent.contract.core.service.pdf.ContractToPdfModelMapper;
import java.io.InputStream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContractPdfUseCaseImpl implements ContractPdfUseCase {

  private final ContractLoadPort loadPort;
  private final ContractToPdfConverter converter;
  private final ContractToPdfModelMapper mapper;

  @Override
  public InputStream getPdfInputStreamById(final Long id) {
    final var domain = loadPort.loadById(id);
    final var pdfModel = mapper.getPdfModel(domain);

    return converter.getPdfInputStream(pdfModel);
  }
}
