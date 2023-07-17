package Canhão;
import robocode.*;
import java.awt.Color;
/**
 * Canhão - a class by (Gabriela Gomes da Fonseca)
 */

public class Canhão extends AdvancedRobot {//oferece funcionalidades adicionais e avançadas doq a classe Robot

    public void run() {//equivale ao metodo main, igual quando trabalha console Java, a diferença é que esse implementa threads 
        setColors(Color.darkBlack, Color.black, Color.lightGray, Color.white, Color.green);//personalizando cores do robô

        setMaxVelocity(8); // Define a velocidade máxima para 8

        while (true) {//movimentação principal do robô
            setAhead(100); // Avança 100 pixels
            setTurnRight(90); // Gira 90 graus à direita
            execute(); // Executa as ações do robô		 
        }
    }
	
	/*Evento: onScannedRobot(ScannedRobotEvent e) --> executa quando radar localiza o tanque do inimigo
	Como ação pode executar outros metodos da API. 
	Método fire dispara um tiro de canhão na intensidade escolhida (canhão consome energia na proporção da intensidade do tiro), 
	se acertar o tiro, a energia é renovada na mesma intensidade.
	cada tiro gera calor e o canhão só dispara quando esfria(esse calor), o calor gerado também é proporcional a potencia do disparo.*/
  
	//tank robô inimigo detectado
    public void onScannedRobot(ScannedRobotEvent e) {
        double distancia = e.getDistance();//obtem a distancia em pixels do tank robô detectado (metodo disponivel na documentação oficial do Robocode)
        if (distancia < 150) {//se tanque inimigo estiver perto...
            setFire(3); // Dispara um tiro com potência 3
            setTurnRight(e.getBearing()); // Gira o robô na direção do robô inimigo
            setAhead(distancia - 100); // Move em direção ao robô inimigo com uma distância de segurança
        } else {//senão...
            setFire(2.5); // Dispara um tiro com potência 1
            setTurnRight(e.getBearing()); // Gira o robô na direção do robô inimigo
            setAhead(100); // Avança 100 pixels
        }
        execute(); // Executa as ações do robô
    }
	
	/*estrategia para caso robô bater na parede e ficar preso
	onHitWall() --> evento que identifica que robô colidiu com a parede */
	
	//Em caso de colisão com a parede
    public void onHitWall(HitWallEvent e) {
		/*Se o robô colidir com a parede retorne 50 pixels(baseado no tamanho do robô(45 pixels)
	    Depois que retornar gire 90 graus a direita (baseando na lógica principal usada para movimentação do robô --> setturnRight(90);))*/
        setBack(50);
        setTurnRight(90);
        execute(); // Executa as ações do robô
    }
}
/*acesso a documentação --> Robocode API (no site do Robocode)
Dica: estudar código de robôs de exemplos*/