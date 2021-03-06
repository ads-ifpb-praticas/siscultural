package io.github.siscultural.services;

import io.github.siscultural.entities.Accomplishment;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.PaymentProposal;
import io.github.siscultural.entities.Presentation;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.AccomplishmentRepository;
import io.github.siscultural.repositories.ContractRepository;
import io.github.siscultural.repositories.PaymentProposalRepository;
import io.github.siscultural.repositories.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by susanneferraz on 16/09/16.
 */
@Service
public class ContractService {

    @Autowired
    private static PresentationRepository presentationRepository;
    private static ContractRepository contractRepository;
    private static AccomplishmentRepository accomplishmentRepository;
    private static PaymentProposalRepository paymentProposalRepository;

    @Autowired
    public ContractService(PresentationRepository presentationRepository, ContractRepository contractRepository,
                           AccomplishmentRepository accomplishmentRepository, PaymentProposalRepository paymentProposalRepository) {
        this.presentationRepository = presentationRepository;
        this.contractRepository = contractRepository;
        this.accomplishmentRepository = accomplishmentRepository;
        this.paymentProposalRepository = paymentProposalRepository;
    }

    public Contract findById(Long id) {
        return contractRepository.findById(id);
    }

    public List<Contract> findAll () {
        return contractRepository.findAll();
    }

    public Contract save (Contract contract) {

//        if (presentation.getId() != null) {
//            return presentationRepository.updateExists(presentation);
//        }

        contract.setContractDate(LocalDate.now());

        return contractRepository.save(contract);
    }

    public Map<String, String> delete(Contract contract) {

        Long id = contract.getId();

        Map<String, String> map = new HashMap<>();
        map.clear();

        if (contract != null) {

            //buscando accomplishments do
            List<Accomplishment> accomplishments = accomplishmentRepository.findByContract(contract);

            //apagando os accomplishments do banco
            for (Accomplishment ac : accomplishments) {
                accomplishmentRepository.delete(ac);
            }

            //buscando accomplishments do
            List<PaymentProposal> paymentProposals = paymentProposalRepository.findByContract(contract);

            //apagando os accomplishments do banco
            for (PaymentProposal paymentProposal : paymentProposals) {
                paymentProposalRepository.delete(paymentProposal);
            }

            contractRepository.delete(contract);

            contract = contractRepository.findById(id);

            if (contract != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        } else {
            map.put("error", "Contrato não encontrada.");
        }

        return map;
    }


}
