package br.com.zup.nossocartao.propostas.service;

import br.com.zup.nossocartao.propostas.PropostaStatus;

public enum AnalisePropostaStatus {

    COM_RESTRICAO {
        @Override
        public PropostaStatus toPropostaStatus() {
            return PropostaStatus.NAO_ELEGIVEL;
        }

    },
    SEM_RESTRICAO {
        @Override
        public PropostaStatus toPropostaStatus() {
            return PropostaStatus.ELEGIVEL;
        }

    };

    public abstract PropostaStatus toPropostaStatus();

}