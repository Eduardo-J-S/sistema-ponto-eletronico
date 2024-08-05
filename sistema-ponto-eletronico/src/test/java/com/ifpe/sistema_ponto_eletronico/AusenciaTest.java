import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import com.ifpe.sistema_ponto_eletronico.model.Ausencia;
import com.ifpe.sistema_ponto_eletronico.model.Funcionario;
import com.ifpe.sistema_ponto_eletronico.model.TipoRegistro;
public class AusenciaTest {

    private Ausencia ausencia;

    @BeforeEach
    public void setUp() {
        ausencia = new Ausencia();
    }

    @Test
    public void testAusenciaAttributes() {
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fim = LocalDate.of(2024, 1, 31);
        Funcionario funcionario = new Funcionario();

        ausencia.setIdAusencia(1L);
        
        
        ausencia.setTipoAusencia(TipoRegistro.FERIAS); 
        
        ausencia.setDataInicio(inicio);
        ausencia.setDataFim(fim);
        ausencia.setFuncionario(funcionario);

        assertAll("Ausencia attributes",
            () -> assertEquals(1L, ausencia.getIdAusencia()),
            () -> assertEquals(TipoRegistro.FERIAS, ausencia.getTipoAusencia()), 
            () -> assertEquals(inicio, ausencia.getDataInicio()),
            () -> assertEquals(fim, ausencia.getDataFim()),
            () -> assertEquals(funcionario, ausencia.getFuncionario())
        );
    }
}
