//Clase Java Generics para pasar un objeto a imprimir
package pdinfp_vista;

public class ImprimirJavaGenerics<T> {

        //Atributo
        private  T objetoParaImprimir;

        // Constructor
        public ImprimirJavaGenerics(T objetoParaImprimir) {
            this.objetoParaImprimir = objetoParaImprimir;
        }

        // Método que imprime el objeto pasado como T
        public void imprimir() {
            System.out.println(objetoParaImprimir);
        }
}
