package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Costanti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	private List<Attrezzo> attrezzi;
	
	private int pesoMax;
	
	public Borsa() {
		this(Costanti.getPesoMaxBorsa());
	}
	public Borsa(int pesoMax) {
		this.pesoMax=pesoMax;
		this.attrezzi=new ArrayList<Attrezzo>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.add(attrezzo);
	}
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> i=this.attrezzi.iterator();
		while(i.hasNext()) {
			Attrezzo a=i.next();
			if(a.getNome().equals(nomeAttrezzo)) return a;
		}
		return null;
	}
	
	public int getPeso() {
		int peso=0;
		Iterator<Attrezzo> i=this.attrezzi.iterator();
		while(i.hasNext()) {
			Attrezzo a=i.next();
			peso=peso+a.getPeso();
			}
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a=this.getAttrezzo(nomeAttrezzo);
		this.attrezzi.remove(a);
		return a;
	}
	
	public String toString() {
		StringBuilder s= new StringBuilder();
		Iterator<Attrezzo> i=this.attrezzi.iterator();
		if(!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			while(i.hasNext()){
				Attrezzo a=i.next();
				s.append(a.toString()+" ");
			}
		}
		else	s.append("Borsa vuota");
		return s.toString();		
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		ArrayList<Attrezzo> listaOrdinata = new ArrayList<Attrezzo>(this.attrezzi);
		Collections.sort(listaOrdinata);
		return listaOrdinata;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		class ComparatoreAttrezziPerNome implements Comparator<Attrezzo>{
			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				return o1.getNome().compareTo(o2.getNome());
			}
		}
		ComparatoreAttrezziPerNome c=new ComparatoreAttrezziPerNome();
		SortedSet<Attrezzo> setOrdinatoPerNome=new TreeSet<>(c);
		setOrdinatoPerNome.addAll(this.attrezzi);
		return setOrdinatoPerNome;
		
	}

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map <Integer,Set<Attrezzo>> mappaOrdinataPerPeso = new HashMap<>();
		Set<Attrezzo> stessoPeso;
		for (Attrezzo a:this.attrezzi) {
			stessoPeso=mappaOrdinataPerPeso.get(a.getPeso());
			if(stessoPeso==null) {
				stessoPeso=new HashSet<>();
			}
			stessoPeso.add(a);
			mappaOrdinataPerPeso.put(a.getPeso(),stessoPeso);
		}
	return mappaOrdinataPerPeso;
	}
	
	}
