package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final LinkedList<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		if (myStationnements.isEmpty() || !myStationnements.getLast().estEnCours()){
			Stationnement s = new Stationnement(this, g);
			myStationnements.add(s);
		}else{
			throw new Exception("La voiture est déjà dans un garage");
		}
	}

	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		// DONE: Implémenter cette méthode
		// Trouver le dernier stationnement de la voiture
		// Terminer ce stationnement

		if (this.estDansUnGarage()){
			myStationnements.getLast().terminer();  
		}else{
			throw new Exception("La voiture n'est pas dans un garage");
		}
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		// DONE: Implémenter cette méthode
            HashSet<Garage> setGarage = new HashSet<>();
            for ( Stationnement stationnement : myStationnements){
                setGarage.add(stationnement.getGarage());
            }
            return setGarage;
        }

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// DONE: Implémenter cette méthode
		// Vrai si le dernier stationnement est en cours

		if (!myStationnements.isEmpty() && myStationnements.getLast().estEnCours()){
			return true;
		}else{
			return false;
		}
	}
        
        
	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		// DONE: Implémenter cette méthode
                
			Map<Garage,List<Stationnement>> list = getStationnementByGarages();

			for( Garage g : list.keySet() ){
				out.println(g.toString());
				for (Stationnement stat : list.get(g) ){
					out.println("\t"+stat.toString());
				}
			}
        }
        
        private Map<Garage,List<Stationnement>> getStationnementByGarages(){
            
            Map<Garage,List<Stationnement>> map = new HashMap<>();
            for ( Stationnement stationnement : myStationnements){
                if(map.containsKey(stationnement.getGarage())){
                    map.get(stationnement.getGarage()).add(stationnement);
                }else{
                    map.put(stationnement.getGarage(), new ArrayList<Stationnement>(){{add(stationnement);}});
                }
            }
            return map;
        }
        
        

}
