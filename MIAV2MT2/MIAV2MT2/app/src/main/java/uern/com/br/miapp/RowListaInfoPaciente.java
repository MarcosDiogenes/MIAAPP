package uern.com.br.miapp;

public class RowListaInfoPaciente {

    private String tipodisfagia, tipoalimento, duracao, datahora;

    public RowListaInfoPaciente(String tipodisfagia, String tipoalimento, String duracao, String datahora) {

        this.setTipodisfagia(tipodisfagia);
        this.setTipoalimento(tipoalimento);
        this.setDuracao(duracao);
        this.setDatahora(datahora);

    }

    public String getTipodisfagia() {
        return tipodisfagia;
    }

    public void setTipodisfagia(String tipodisfagia) {
        this.tipodisfagia = tipodisfagia;
    }

    public String getTipoalimento() {
        return tipoalimento;
    }

    public void setTipoalimento(String tipoalimento) {
        this.tipoalimento = tipoalimento;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDatahora() {
        return datahora;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }
}
