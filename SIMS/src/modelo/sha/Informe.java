package modelo.sha;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import modelo.maestros.Empresa;
import modelo.maestros.Paciente;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "informe", schema = "dusa_sims.dbo")
public class Informe implements Serializable {

	private static final long serialVersionUID = -5110967796816980541L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_informe", unique = true, nullable = false)
	private long idInforme;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_a", referencedColumnName = "id_paciente")
	private Paciente paciente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_b", referencedColumnName = "id_paciente")
	private Paciente pacienteB;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_c", referencedColumnName = "id_paciente")
	private Paciente pacienteC;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_d", referencedColumnName = "id_paciente")
	private Paciente pacienteD;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_e", referencedColumnName = "id_paciente")
	private Paciente pacienteE;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_f", referencedColumnName = "id_paciente")
	private Paciente pacienteF;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_g", referencedColumnName = "id_paciente")
	private Paciente pacienteG;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_h", referencedColumnName = "id_paciente")
	private Paciente pacienteH;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_i", referencedColumnName = "id_paciente")
	private Paciente pacienteI;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_j", referencedColumnName = "id_paciente")
	private Paciente pacienteJ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_k", referencedColumnName = "id_paciente")
	private Paciente pacienteK;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_l", referencedColumnName = "id_paciente")
	private Paciente pacienteL;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente_m", referencedColumnName = "id_paciente")
	private Paciente pacienteM;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_area", referencedColumnName = "id_area")
	private Area area;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa_a", referencedColumnName = "id_empresa")
	private Empresa empresa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa_b", referencedColumnName = "id_empresa")
	private Empresa empresaB;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clasificacion_accidente", referencedColumnName = "id_clasificacion_accidente")
	private ClasificacionAccidente clasificacion;

	@ManyToMany
	@JoinTable(name = "informe_condicion_a", schema = "dusa_sims.dbo", joinColumns = { @JoinColumn(name = "id_informe", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_condicion", nullable = false) })
	private Set<Condicion> condicionA;

	@ManyToMany
	@JoinTable(name = "informe_condicion_b", schema = "dusa_sims.dbo", joinColumns = { @JoinColumn(name = "id_informe", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_condicion", nullable = false) })
	private Set<Condicion> condicionB;

	@ManyToMany
	@JoinTable(name = "informe_condicion_c", schema = "dusa_sims.dbo", joinColumns = { @JoinColumn(name = "id_informe", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_condicion", nullable = false) })
	private Set<Condicion> condicionC;

	@ManyToMany
	@JoinTable(name = "informe_condicion_d", schema = "dusa_sims.dbo", joinColumns = { @JoinColumn(name = "id_informe", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_condicion", nullable = false) })
	private Set<Condicion> condicionD;

	@ManyToMany
	@JoinTable(name = "informe_condicion_e", schema = "dusa_sims.dbo", joinColumns = { @JoinColumn(name = "id_informe", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_condicion", nullable = false) })
	private Set<Condicion> condicionE;

	@ManyToMany
	@JoinTable(name = "informe_condicion_f", schema = "dusa_sims.dbo", joinColumns = { @JoinColumn(name = "id_informe", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_condicion", nullable = false) })
	private Set<Condicion> condicionF;

	@Column(name = "fecha_visita")
	private Timestamp fechaVisita;
	
	@Column(length = 1000)
	private String ordenamientos;
	
	@Column(length = 1000)
	private String funcionario;

	@Column(length = 50)
	private String ebc;

	@Column(length = 50)
	private String ebcf;

	@Column(length = 50)
	private String ebd;

	@Column(length = 50)
	private String ebe;

	@Column(length = 50)
	private String ebeg;

	@Column(length = 50)
	private String ebf;

	@Column(length = 50)
	private String ebg;

	@Column(length = 50)
	private String ebge;

	@Column(length = 50)
	private String codigo;

	@Column(name = "fa")
	private Timestamp fa;

	@Column(length = 50)
	private String fb;

	@Column(length = 10)
	private String fc;

	@Column(length = 250)
	private String fd;

	@Column(length = 250)
	private String fe;

	@Column(length = 250)
	private String ff;

	@Column(length = 250)
	private String fga;

	@Column(length = 250)
	private String fgb;

	@Column(length = 250)
	private String fgc;

	@Column(length = 250)
	private String fgd;

	@Column(length = 250)
	private String fge;

	@Column(length = 250)
	private String fgf;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean fgga;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean fgha;

	@Column(length = 250)
	private String fgh;

	@Column(length = 250)
	private String fgi;

	@Column(length = 250)
	private String fgj;

	@Column(length = 250)
	private String fgaa;

	@Column(length = 250)
	private String fgab;

	@Column(length = 250)
	private String fgad;

	@Column(length = 250)
	private String gaa;

	@Column(length = 250)
	private String haa;

	@Column(length = 250)
	private String hab;

	@Column(length = 250)
	private String hac;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hada;

	@Column(length = 250)
	private String haea;

	@Column(length = 250)
	private String hae;

	@Column(length = 250)
	private String haf;

	@Column(length = 250)
	private String hba;

	@Column(length = 250)
	private String hbc;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hbd;

	@Column(length = 250)
	private String hbda;

	@Column(length = 250)
	private String hbe;

	@Column(length = 250)
	private String hbea;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hbf;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hbg;

	@Column(length = 250)
	private String hbh;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hbaaa;

	@Column(length = 250)
	private String hbaa;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hbaab;

	@Column(length = 250)
	private String hbab;

	@Column(length = 250)
	private String hca;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcb;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcc;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcd;

	@Column(length = 250)
	private String hcea;

	@Column(length = 250)
	private String hce;

	@Column(length = 250)
	private String hcfa;

	@Column(length = 250)
	private String hcf;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcg;

	@Column(length = 250)
	private String hcha;

	@Column(length = 250)
	private String hch;

	@Column(length = 250)
	private String hcia;

	@Column(length = 250)
	private String hci;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcj;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcaa;

	@Column(length = 250)
	private String hcaaa;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcab;

	@Column(length = 250)
	private String hcaba;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcac;

	@Column(length = 250)
	private String hcad;

	@Column(length = 250)
	private String hcae;

	@Column(length = 250)
	private String hcaea;

	@Column(length = 250)
	private String hcaf;

	@Column(length = 250)
	private String hcafa;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcaga;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcagb;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcagc;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcagd;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcage;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcagf;

	@Column(length = 250)
	private String hcagfa;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcagg;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcagh;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hcagi;

	@Column(length = 250)
	private String hda;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean hdb;

	@Column(length = 250)
	private String hdba;

	@Column(length = 250)
	private String hdc;

	@Column(length = 250)
	private String hdca;

	@Column(length = 250)
	private String hdd;

	@Column(length = 250)
	private String hdea;

	@Column(length = 250)
	private String hdeb;

	@Column(length = 250)
	private String hdec;

	@Column(length = 250)
	private String hded;

	@Column(length = 250)
	private String hdf;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean iaa;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean iab;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean iac;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean iad;

	@Column(length = 250)
	private String iada;

	@Column(name = "iae")
	private Timestamp iae;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean iaf;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean iag;

	@Column(length = 250)
	private String iaga;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean iah;

	@Column(length = 250)
	private String iai;

	@Column(length = 250)
	private String iaj;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean iba;

	@Column(name = "ibb")
	private Timestamp ibb;

	@Column(length = 250)
	private String ibc;

	@Column(name = "ibd")
	private Timestamp ibd;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ibe;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ibf;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ibg;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ibh;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ibi;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ibj;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ibaa;

	@Column(length = 250)
	private String ibab;

	@Column(length = 250)
	private String ibac;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ica;

	@Column(name = "icb")
	private Timestamp icb;

	@Column(length = 250)
	private String icc;

	@Column(length = 250)
	private String icd;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ice;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean icf;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean icg;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ich;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ici;

	@Column(name = "icj")
	private Timestamp icj;

	@Column(length = 250)
	private String icaa;

	@Column(length = 250)
	private String icab;

	@Column(length = 250)
	private String icac;

	@Column(length = 250)
	private String icae;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ida;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean idb;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean idc;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean idd;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ide;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean idf;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean idg;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean idh;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean idi;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean idj;

	@Column(name = "idaa")
	private Timestamp idaa;

	@Column(length = 250)
	private String idab;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean idac;

	@Column(length = 250)
	private String idad;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	@OneToMany(mappedBy = "informe")
	private Set<PlanAccion> planes;
	
	@Lob
	@Column(name = "imagen_a")
	private byte[] imagenA;
	
	@Lob
	@Column(name = "imagen_b")
	private byte[] imagenB;
	
	@Lob
	@Column(name = "imagen_c")
	private byte[] imagenC;
	
	@Lob
	@Column(name = "imagen_d")
	private byte[] imagenD;
	
	@Lob
	@Column(name = "imagen_e")
	private byte[] imagenE;
	
	@Column(name = "obs_imagen_a", length = 1000)
	private String obsImagenA;
	
	@Column(name = "obs_imagen_b", length = 1000)
	private String obsImagenB;
	
	@Column(name = "obs_imagen_c", length = 1000)
	private String obsImagenC;
	
	@Column(name = "obs_imagen_d", length = 1000)
	private String obsImagenD;
	
	@Column(name = "obs_imagen_e", length = 1000)
	private String obsImagenE;
	
	@Column(name = "seleccionada_a", length = 10)
	private String seleccionadaA;
	
	@Column(name = "seleccionada_b", length = 10)
	private String seleccionadaB;
	

	public Informe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Informe(long idInforme, Paciente pacienteA, Paciente pacienteB,
			Paciente pacienteC, Paciente pacienteD, Paciente pacienteE,
			Paciente pacienteF, Paciente pacienteG, Paciente pacienteH,
			Paciente pacienteI, Paciente pacienteJ, Paciente pacienteK,
			Paciente pacienteL, Paciente pacienteM, Area area,
			Empresa empresaA, Empresa empresaB,
			ClasificacionAccidente clasificacion, Set<Condicion> condicionA,
			Set<Condicion> condicionB, Set<Condicion> condicionC,
			Set<Condicion> condicionD, Set<Condicion> condicionE,
			Set<Condicion> condicionF, String codigo, Timestamp fa, String fb,
			String fc, String fd, String fe, String ff, String fga, String fgb,
			String fgc, String fgd, String fge, String fgf, Boolean fgga,
			Boolean fgha, String fgh, String fgi, String fgj, String fgaa,
			String fgab, String fgad, String gaa, String haa, String hab,
			String hac, Boolean hada, String haea, String hae, String haf,
			String hba, String hbc, Boolean hbd, String hbda, String hbe,
			String hbea, Boolean hbf, Boolean hbg, String hbh, Boolean hbaaa,
			String hbaa, Boolean hbaab, String hbab, String hca, Boolean hcb,
			Boolean hcc, Boolean hcd, String hcea, String hce, String hcfa,
			String hcf, Boolean hcg, String hcha, String hch, String hcia,
			String hci, Boolean hcj, Boolean hcaa, String hcaaa, Boolean hcab,
			String hcaba, Boolean hcac, String hcad, String hcae, String hcaea,
			String hcaf, Boolean hcaga, Boolean hcagb, Boolean hcagc,
			Boolean hcagd, Boolean hcage, Boolean hcagf, String hcagfa,
			Boolean hcagg, Boolean hcagh, Boolean hcagi, String hda,
			Boolean hdb, String hdba, String hdc, String hdca, String hdd,
			String hdea, String hdeb, String hdec, String hded, String hdf,
			Boolean iaa, Boolean iab, Boolean iac, Boolean iad, String iada,
			Timestamp iae, Boolean iaf, Boolean iag, String iaga, Boolean iah,
			String iai, String iaj, Boolean iba, Timestamp ibb, String ibc,
			Timestamp ibd, Boolean ibe, Boolean ibf, Boolean ibg, Boolean ibh,
			Boolean ibi, Boolean ibj, Boolean ibaa, String ibab, String ibac,
			Boolean ica, Timestamp icb, String icc, String icd, Boolean ice,
			Boolean icf, Boolean icg, Boolean ich, Boolean ici, Timestamp icj,
			String icaa, String icab, String icac, String icae, Boolean ida,
			Boolean idb, Boolean idc, Boolean idd, Boolean ide, Boolean idf,
			Boolean idg, Boolean idh, Boolean idi, Boolean idj, Timestamp idaa,
			String idab, Boolean idac, String idad, Timestamp fecha,
			String hora, String usuario) {
		super();
		this.idInforme = idInforme;
		this.paciente = pacienteA;
		this.pacienteB = pacienteB;
		this.pacienteC = pacienteC;
		this.pacienteD = pacienteD;
		this.pacienteE = pacienteE;
		this.pacienteF = pacienteF;
		this.pacienteG = pacienteG;
		this.pacienteH = pacienteH;
		this.pacienteI = pacienteI;
		this.pacienteJ = pacienteJ;
		this.pacienteK = pacienteK;
		this.pacienteL = pacienteL;
		this.pacienteM = pacienteM;
		this.area = area;
		this.empresa = empresaA;
		this.empresaB = empresaB;
		this.clasificacion = clasificacion;
		this.condicionA = condicionA;
		this.condicionB = condicionB;
		this.condicionC = condicionC;
		this.condicionD = condicionD;
		this.condicionE = condicionE;
		this.condicionF = condicionF;
		this.codigo = codigo;
		this.fa = fa;
		this.fb = fb;
		this.fc = fc;
		this.fd = fd;
		this.fe = fe;
		this.ff = ff;
		this.fga = fga;
		this.fgb = fgb;
		this.fgc = fgc;
		this.fgd = fgd;
		this.fge = fge;
		this.fgf = fgf;
		this.fgga = fgga;
		this.fgha = fgha;
		this.fgh = fgh;
		this.fgi = fgi;
		this.fgj = fgj;
		this.fgaa = fgaa;
		this.fgab = fgab;
		this.fgad = fgad;
		this.gaa = gaa;
		this.haa = haa;
		this.hab = hab;
		this.hac = hac;
		this.hada = hada;
		this.haea = haea;
		this.hae = hae;
		this.haf = haf;
		this.hba = hba;
		this.hbc = hbc;
		this.hbd = hbd;
		this.hbda = hbda;
		this.hbe = hbe;
		this.hbea = hbea;
		this.hbf = hbf;
		this.hbg = hbg;
		this.hbh = hbh;
		this.hbaaa = hbaaa;
		this.hbaa = hbaa;
		this.hbaab = hbaab;
		this.hbab = hbab;
		this.hca = hca;
		this.hcb = hcb;
		this.hcc = hcc;
		this.hcd = hcd;
		this.hcea = hcea;
		this.hce = hce;
		this.hcfa = hcfa;
		this.hcf = hcf;
		this.hcg = hcg;
		this.hcha = hcha;
		this.hch = hch;
		this.hcia = hcia;
		this.hci = hci;
		this.hcj = hcj;
		this.hcaa = hcaa;
		this.hcaaa = hcaaa;
		this.hcab = hcab;
		this.hcaba = hcaba;
		this.hcac = hcac;
		this.hcad = hcad;
		this.hcae = hcae;
		this.hcaea = hcaea;
		this.hcaf = hcaf;
		this.hcaga = hcaga;
		this.hcagb = hcagb;
		this.hcagc = hcagc;
		this.hcagd = hcagd;
		this.hcage = hcage;
		this.hcagf = hcagf;
		this.hcagfa = hcagfa;
		this.hcagg = hcagg;
		this.hcagh = hcagh;
		this.hcagi = hcagi;
		this.hda = hda;
		this.hdb = hdb;
		this.hdba = hdba;
		this.hdc = hdc;
		this.hdca = hdca;
		this.hdd = hdd;
		this.hdea = hdea;
		this.hdeb = hdeb;
		this.hdec = hdec;
		this.hded = hded;
		this.hdf = hdf;
		this.iaa = iaa;
		this.iab = iab;
		this.iac = iac;
		this.iad = iad;
		this.iada = iada;
		this.iae = iae;
		this.iaf = iaf;
		this.iag = iag;
		this.iaga = iaga;
		this.iah = iah;
		this.iai = iai;
		this.iaj = iaj;
		this.iba = iba;
		this.ibb = ibb;
		this.ibc = ibc;
		this.ibd = ibd;
		this.ibe = ibe;
		this.ibf = ibf;
		this.ibg = ibg;
		this.ibh = ibh;
		this.ibi = ibi;
		this.ibj = ibj;
		this.ibaa = ibaa;
		this.ibab = ibab;
		this.ibac = ibac;
		this.ica = ica;
		this.icb = icb;
		this.icc = icc;
		this.icd = icd;
		this.ice = ice;
		this.icf = icf;
		this.icg = icg;
		this.ich = ich;
		this.ici = ici;
		this.icj = icj;
		this.icaa = icaa;
		this.icab = icab;
		this.icac = icac;
		this.icae = icae;
		this.ida = ida;
		this.idb = idb;
		this.idc = idc;
		this.idd = idd;
		this.ide = ide;
		this.idf = idf;
		this.idg = idg;
		this.idh = idh;
		this.idi = idi;
		this.idj = idj;
		this.idaa = idaa;
		this.idab = idab;
		this.idac = idac;
		this.idad = idad;
		this.fechaAuditoria = fecha;
		this.horaAuditoria = hora;
		this.usuarioAuditoria = usuario;
	}

	public long getIdInforme() {
		return idInforme;
	}

	public void setIdInforme(long idInforme) {
		this.idInforme = idInforme;
	}

	public Paciente getPacienteA() {
		return paciente;
	}

	public void setPacienteA(Paciente pacienteA) {
		this.paciente = pacienteA;
	}

	public Paciente getPacienteB() {
		return pacienteB;
	}

	public void setPacienteB(Paciente pacienteB) {
		this.pacienteB = pacienteB;
	}

	public Paciente getPacienteC() {
		return pacienteC;
	}

	public void setPacienteC(Paciente pacienteC) {
		this.pacienteC = pacienteC;
	}

	public Paciente getPacienteD() {
		return pacienteD;
	}

	public void setPacienteD(Paciente pacienteD) {
		this.pacienteD = pacienteD;
	}

	public Paciente getPacienteE() {
		return pacienteE;
	}

	public void setPacienteE(Paciente pacienteE) {
		this.pacienteE = pacienteE;
	}

	public Paciente getPacienteF() {
		return pacienteF;
	}

	public void setPacienteF(Paciente pacienteF) {
		this.pacienteF = pacienteF;
	}

	public Paciente getPacienteG() {
		return pacienteG;
	}

	public void setPacienteG(Paciente pacienteG) {
		this.pacienteG = pacienteG;
	}

	public Paciente getPacienteH() {
		return pacienteH;
	}

	public void setPacienteH(Paciente pacienteH) {
		this.pacienteH = pacienteH;
	}

	public Paciente getPacienteI() {
		return pacienteI;
	}

	public void setPacienteI(Paciente pacienteI) {
		this.pacienteI = pacienteI;
	}

	public Paciente getPacienteJ() {
		return pacienteJ;
	}

	public void setPacienteJ(Paciente pacienteJ) {
		this.pacienteJ = pacienteJ;
	}

	public Paciente getPacienteK() {
		return pacienteK;
	}

	public void setPacienteK(Paciente pacienteK) {
		this.pacienteK = pacienteK;
	}

	public Paciente getPacienteL() {
		return pacienteL;
	}

	public void setPacienteL(Paciente pacienteL) {
		this.pacienteL = pacienteL;
	}

	public Paciente getPacienteM() {
		return pacienteM;
	}

	public void setPacienteM(Paciente pacienteM) {
		this.pacienteM = pacienteM;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Empresa getEmpresaA() {
		return empresa;
	}

	public void setEmpresaA(Empresa empresaA) {
		this.empresa = empresaA;
	}

	public Empresa getEmpresaB() {
		return empresaB;
	}

	public void setEmpresaB(Empresa empresaB) {
		this.empresaB = empresaB;
	}

	public ClasificacionAccidente getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(ClasificacionAccidente clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Set<Condicion> getCondicionA() {
		return condicionA;
	}

	public void setCondicionA(Set<Condicion> condicionA) {
		this.condicionA = condicionA;
	}

	public Set<Condicion> getCondicionB() {
		return condicionB;
	}

	public void setCondicionB(Set<Condicion> condicionB) {
		this.condicionB = condicionB;
	}

	public Set<Condicion> getCondicionC() {
		return condicionC;
	}

	public void setCondicionC(Set<Condicion> condicionC) {
		this.condicionC = condicionC;
	}

	public Set<Condicion> getCondicionD() {
		return condicionD;
	}

	public void setCondicionD(Set<Condicion> condicionD) {
		this.condicionD = condicionD;
	}

	public Set<Condicion> getCondicionE() {
		return condicionE;
	}

	public void setCondicionE(Set<Condicion> condicionE) {
		this.condicionE = condicionE;
	}

	public Set<Condicion> getCondicionF() {
		return condicionF;
	}

	public void setCondicionF(Set<Condicion> condicionF) {
		this.condicionF = condicionF;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Timestamp getFa() {
		return fa;
	}

	public void setFa(Timestamp fa) {
		this.fa = fa;
	}

	public String getFb() {
		return fb;
	}

	public void setFb(String fb) {
		this.fb = fb;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public String getFd() {
		return fd;
	}

	public void setFd(String fd) {
		this.fd = fd;
	}

	public String getFe() {
		return fe;
	}

	public void setFe(String fe) {
		this.fe = fe;
	}

	public String getFf() {
		return ff;
	}

	public void setFf(String ff) {
		this.ff = ff;
	}

	public String getFga() {
		return fga;
	}

	public void setFga(String fga) {
		this.fga = fga;
	}

	public String getFgb() {
		return fgb;
	}

	public void setFgb(String fgb) {
		this.fgb = fgb;
	}

	public String getFgc() {
		return fgc;
	}

	public void setFgc(String fgc) {
		this.fgc = fgc;
	}

	public String getFgd() {
		return fgd;
	}

	public void setFgd(String fgd) {
		this.fgd = fgd;
	}

	public String getFge() {
		return fge;
	}

	public void setFge(String fge) {
		this.fge = fge;
	}

	public String getFgf() {
		return fgf;
	}

	public void setFgf(String fgf) {
		this.fgf = fgf;
	}

	public Boolean getFgga() {
		return fgga;
	}

	public void setFgga(Boolean fgga) {
		this.fgga = fgga;
	}

	public Boolean getFgha() {
		return fgha;
	}

	public void setFgha(Boolean fgha) {
		this.fgha = fgha;
	}

	public String getFgh() {
		return fgh;
	}

	public void setFgh(String fgh) {
		this.fgh = fgh;
	}

	public String getFgi() {
		return fgi;
	}

	public void setFgi(String fgi) {
		this.fgi = fgi;
	}

	public String getFgj() {
		return fgj;
	}

	public void setFgj(String fgj) {
		this.fgj = fgj;
	}

	public String getFgaa() {
		return fgaa;
	}

	public void setFgaa(String fgaa) {
		this.fgaa = fgaa;
	}

	public String getFgab() {
		return fgab;
	}

	public void setFgab(String fgab) {
		this.fgab = fgab;
	}

	public String getFgad() {
		return fgad;
	}

	public void setFgad(String fgad) {
		this.fgad = fgad;
	}

	public String getGaa() {
		return gaa;
	}

	public void setGaa(String gaa) {
		this.gaa = gaa;
	}

	public String getHaa() {
		return haa;
	}

	public void setHaa(String haa) {
		this.haa = haa;
	}

	public String getHab() {
		return hab;
	}

	public void setHab(String hab) {
		this.hab = hab;
	}

	public String getHac() {
		return hac;
	}

	public void setHac(String hac) {
		this.hac = hac;
	}

	public Boolean getHada() {
		return hada;
	}

	public void setHada(Boolean hada) {
		this.hada = hada;
	}

	public String getHaea() {
		return haea;
	}

	public void setHaea(String haea) {
		this.haea = haea;
	}

	public String getHae() {
		return hae;
	}

	public void setHae(String hae) {
		this.hae = hae;
	}

	public String getHaf() {
		return haf;
	}

	public void setHaf(String haf) {
		this.haf = haf;
	}

	public String getHba() {
		return hba;
	}

	public void setHba(String hba) {
		this.hba = hba;
	}

	public String getHbc() {
		return hbc;
	}

	public void setHbc(String hbc) {
		this.hbc = hbc;
	}

	public Boolean getHbd() {
		return hbd;
	}

	public void setHbd(Boolean hbd) {
		this.hbd = hbd;
	}

	public String getHbda() {
		return hbda;
	}

	public void setHbda(String hbda) {
		this.hbda = hbda;
	}

	public String getHbe() {
		return hbe;
	}

	public void setHbe(String hbe) {
		this.hbe = hbe;
	}

	public String getHbea() {
		return hbea;
	}

	public void setHbea(String hbea) {
		this.hbea = hbea;
	}

	public Boolean getHbf() {
		return hbf;
	}

	public void setHbf(Boolean hbf) {
		this.hbf = hbf;
	}

	public Boolean getHbg() {
		return hbg;
	}

	public void setHbg(Boolean hbg) {
		this.hbg = hbg;
	}

	public String getHbh() {
		return hbh;
	}

	public void setHbh(String hbh) {
		this.hbh = hbh;
	}

	public Boolean getHbaaa() {
		return hbaaa;
	}

	public void setHbaaa(Boolean hbaaa) {
		this.hbaaa = hbaaa;
	}

	public String getHbaa() {
		return hbaa;
	}

	public void setHbaa(String hbaa) {
		this.hbaa = hbaa;
	}

	public Boolean getHbaab() {
		return hbaab;
	}

	public void setHbaab(Boolean hbaab) {
		this.hbaab = hbaab;
	}

	public String getHbab() {
		return hbab;
	}

	public void setHbab(String hbab) {
		this.hbab = hbab;
	}

	public String getHca() {
		return hca;
	}

	public void setHca(String hca) {
		this.hca = hca;
	}

	public Boolean getHcb() {
		return hcb;
	}

	public void setHcb(Boolean hcb) {
		this.hcb = hcb;
	}

	public Boolean getHcc() {
		return hcc;
	}

	public void setHcc(Boolean hcc) {
		this.hcc = hcc;
	}

	public Boolean getHcd() {
		return hcd;
	}

	public void setHcd(Boolean hcd) {
		this.hcd = hcd;
	}

	public String getHcea() {
		return hcea;
	}

	public void setHcea(String hcea) {
		this.hcea = hcea;
	}

	public String getHce() {
		return hce;
	}

	public void setHce(String hce) {
		this.hce = hce;
	}

	public String getHcfa() {
		return hcfa;
	}

	public void setHcfa(String hcfa) {
		this.hcfa = hcfa;
	}

	public String getHcf() {
		return hcf;
	}

	public void setHcf(String hcf) {
		this.hcf = hcf;
	}

	public Boolean getHcg() {
		return hcg;
	}

	public void setHcg(Boolean hcg) {
		this.hcg = hcg;
	}

	public String getHcha() {
		return hcha;
	}

	public void setHcha(String hcha) {
		this.hcha = hcha;
	}

	public String getHch() {
		return hch;
	}

	public void setHch(String hch) {
		this.hch = hch;
	}

	public String getHcia() {
		return hcia;
	}

	public void setHcia(String hcia) {
		this.hcia = hcia;
	}

	public String getHci() {
		return hci;
	}

	public void setHci(String hci) {
		this.hci = hci;
	}

	public Boolean getHcj() {
		return hcj;
	}

	public void setHcj(Boolean hcj) {
		this.hcj = hcj;
	}

	public Boolean getHcaa() {
		return hcaa;
	}

	public void setHcaa(Boolean hcaa) {
		this.hcaa = hcaa;
	}

	public String getHcaaa() {
		return hcaaa;
	}

	public void setHcaaa(String hcaaa) {
		this.hcaaa = hcaaa;
	}

	public Boolean getHcab() {
		return hcab;
	}

	public void setHcab(Boolean hcab) {
		this.hcab = hcab;
	}

	public String getHcaba() {
		return hcaba;
	}

	public void setHcaba(String hcaba) {
		this.hcaba = hcaba;
	}

	public Boolean getHcac() {
		return hcac;
	}

	public void setHcac(Boolean hcac) {
		this.hcac = hcac;
	}

	public String getHcad() {
		return hcad;
	}

	public void setHcad(String hcad) {
		this.hcad = hcad;
	}

	public String getHcae() {
		return hcae;
	}

	public void setHcae(String hcae) {
		this.hcae = hcae;
	}

	public String getHcaea() {
		return hcaea;
	}

	public void setHcaea(String hcaea) {
		this.hcaea = hcaea;
	}

	public String getHcaf() {
		return hcaf;
	}

	public void setHcaf(String hcaf) {
		this.hcaf = hcaf;
	}

	public Boolean getHcaga() {
		return hcaga;
	}

	public void setHcaga(Boolean hcaga) {
		this.hcaga = hcaga;
	}

	public Boolean getHcagb() {
		return hcagb;
	}

	public void setHcagb(Boolean hcagb) {
		this.hcagb = hcagb;
	}

	public Boolean getHcagc() {
		return hcagc;
	}

	public void setHcagc(Boolean hcagc) {
		this.hcagc = hcagc;
	}

	public Boolean getHcagd() {
		return hcagd;
	}

	public void setHcagd(Boolean hcagd) {
		this.hcagd = hcagd;
	}

	public Boolean getHcage() {
		return hcage;
	}

	public void setHcage(Boolean hcage) {
		this.hcage = hcage;
	}

	public Boolean getHcagf() {
		return hcagf;
	}

	public void setHcagf(Boolean hcagf) {
		this.hcagf = hcagf;
	}

	public String getHcagfa() {
		return hcagfa;
	}

	public void setHcagfa(String hcagfa) {
		this.hcagfa = hcagfa;
	}

	public Boolean getHcagg() {
		return hcagg;
	}

	public void setHcagg(Boolean hcagg) {
		this.hcagg = hcagg;
	}

	public Boolean getHcagh() {
		return hcagh;
	}

	public void setHcagh(Boolean hcagh) {
		this.hcagh = hcagh;
	}

	public Boolean getHcagi() {
		return hcagi;
	}

	public void setHcagi(Boolean hcagi) {
		this.hcagi = hcagi;
	}

	public String getHda() {
		return hda;
	}

	public void setHda(String hda) {
		this.hda = hda;
	}

	public Boolean getHdb() {
		return hdb;
	}

	public void setHdb(Boolean hdb) {
		this.hdb = hdb;
	}

	public String getHdba() {
		return hdba;
	}

	public void setHdba(String hdba) {
		this.hdba = hdba;
	}

	public String getHdc() {
		return hdc;
	}

	public void setHdc(String hdc) {
		this.hdc = hdc;
	}

	public String getHdca() {
		return hdca;
	}

	public void setHdca(String hdca) {
		this.hdca = hdca;
	}

	public String getHdd() {
		return hdd;
	}

	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	public String getHdea() {
		return hdea;
	}

	public void setHdea(String hdea) {
		this.hdea = hdea;
	}

	public String getHdeb() {
		return hdeb;
	}

	public void setHdeb(String hdeb) {
		this.hdeb = hdeb;
	}

	public String getHdec() {
		return hdec;
	}

	public void setHdec(String hdec) {
		this.hdec = hdec;
	}

	public String getHded() {
		return hded;
	}

	public void setHded(String hded) {
		this.hded = hded;
	}

	public String getHdf() {
		return hdf;
	}

	public void setHdf(String hdf) {
		this.hdf = hdf;
	}

	public Boolean getIaa() {
		return iaa;
	}

	public void setIaa(Boolean iaa) {
		this.iaa = iaa;
	}

	public Boolean getIab() {
		return iab;
	}

	public void setIab(Boolean iab) {
		this.iab = iab;
	}

	public Boolean getIac() {
		return iac;
	}

	public void setIac(Boolean iac) {
		this.iac = iac;
	}

	public Boolean getIad() {
		return iad;
	}

	public void setIad(Boolean iad) {
		this.iad = iad;
	}

	public String getIada() {
		return iada;
	}

	public void setIada(String iada) {
		this.iada = iada;
	}

	public Timestamp getIae() {
		return iae;
	}

	public void setIae(Timestamp iae) {
		this.iae = iae;
	}

	public Boolean getIaf() {
		return iaf;
	}

	public void setIaf(Boolean iaf) {
		this.iaf = iaf;
	}

	public Boolean getIag() {
		return iag;
	}

	public void setIag(Boolean iag) {
		this.iag = iag;
	}

	public String getIaga() {
		return iaga;
	}

	public void setIaga(String iaga) {
		this.iaga = iaga;
	}

	public Boolean getIah() {
		return iah;
	}

	public void setIah(Boolean iah) {
		this.iah = iah;
	}

	public String getIai() {
		return iai;
	}

	public void setIai(String iai) {
		this.iai = iai;
	}

	public String getIaj() {
		return iaj;
	}

	public void setIaj(String iaj) {
		this.iaj = iaj;
	}

	public Boolean getIba() {
		return iba;
	}

	public void setIba(Boolean iba) {
		this.iba = iba;
	}

	public Timestamp getIbb() {
		return ibb;
	}

	public void setIbb(Timestamp ibb) {
		this.ibb = ibb;
	}

	public String getIbc() {
		return ibc;
	}

	public void setIbc(String ibc) {
		this.ibc = ibc;
	}

	public Timestamp getIbd() {
		return ibd;
	}

	public void setIbd(Timestamp ibd) {
		this.ibd = ibd;
	}

	public Boolean getIbe() {
		return ibe;
	}

	public void setIbe(Boolean ibe) {
		this.ibe = ibe;
	}

	public Boolean getIbf() {
		return ibf;
	}

	public void setIbf(Boolean ibf) {
		this.ibf = ibf;
	}

	public Boolean getIbg() {
		return ibg;
	}

	public void setIbg(Boolean ibg) {
		this.ibg = ibg;
	}

	public Boolean getIbh() {
		return ibh;
	}

	public void setIbh(Boolean ibh) {
		this.ibh = ibh;
	}

	public Boolean getIbi() {
		return ibi;
	}

	public void setIbi(Boolean ibi) {
		this.ibi = ibi;
	}

	public Boolean getIbj() {
		return ibj;
	}

	public void setIbj(Boolean ibj) {
		this.ibj = ibj;
	}

	public Boolean getIbaa() {
		return ibaa;
	}

	public void setIbaa(Boolean ibaa) {
		this.ibaa = ibaa;
	}

	public String getIbab() {
		return ibab;
	}

	public void setIbab(String ibab) {
		this.ibab = ibab;
	}

	public String getIbac() {
		return ibac;
	}

	public void setIbac(String ibac) {
		this.ibac = ibac;
	}

	public Boolean getIca() {
		return ica;
	}

	public void setIca(Boolean ica) {
		this.ica = ica;
	}

	public Timestamp getIcb() {
		return icb;
	}

	public void setIcb(Timestamp icb) {
		this.icb = icb;
	}

	public String getIcc() {
		return icc;
	}

	public void setIcc(String icc) {
		this.icc = icc;
	}

	public String getIcd() {
		return icd;
	}

	public void setIcd(String icd) {
		this.icd = icd;
	}

	public Boolean getIce() {
		return ice;
	}

	public void setIce(Boolean ice) {
		this.ice = ice;
	}

	public Boolean getIcf() {
		return icf;
	}

	public void setIcf(Boolean icf) {
		this.icf = icf;
	}

	public Boolean getIcg() {
		return icg;
	}

	public void setIcg(Boolean icg) {
		this.icg = icg;
	}

	public Boolean getIch() {
		return ich;
	}

	public void setIch(Boolean ich) {
		this.ich = ich;
	}

	public Boolean getIci() {
		return ici;
	}

	public void setIci(Boolean ici) {
		this.ici = ici;
	}

	public Timestamp getIcj() {
		return icj;
	}

	public void setIcj(Timestamp icj) {
		this.icj = icj;
	}

	public String getIcaa() {
		return icaa;
	}

	public void setIcaa(String icaa) {
		this.icaa = icaa;
	}

	public String getIcab() {
		return icab;
	}

	public void setIcab(String icab) {
		this.icab = icab;
	}

	public String getIcac() {
		return icac;
	}

	public void setIcac(String icac) {
		this.icac = icac;
	}

	public String getIcae() {
		return icae;
	}

	public void setIcae(String icae) {
		this.icae = icae;
	}

	public Boolean getIda() {
		return ida;
	}

	public void setIda(Boolean ida) {
		this.ida = ida;
	}

	public Boolean getIdb() {
		return idb;
	}

	public void setIdb(Boolean idb) {
		this.idb = idb;
	}

	public Boolean getIdc() {
		return idc;
	}

	public void setIdc(Boolean idc) {
		this.idc = idc;
	}

	public Boolean getIdd() {
		return idd;
	}

	public void setIdd(Boolean idd) {
		this.idd = idd;
	}

	public Boolean getIde() {
		return ide;
	}

	public void setIde(Boolean ide) {
		this.ide = ide;
	}

	public Boolean getIdf() {
		return idf;
	}

	public void setIdf(Boolean idf) {
		this.idf = idf;
	}

	public Boolean getIdg() {
		return idg;
	}

	public void setIdg(Boolean idg) {
		this.idg = idg;
	}

	public Boolean getIdh() {
		return idh;
	}

	public void setIdh(Boolean idh) {
		this.idh = idh;
	}

	public Boolean getIdi() {
		return idi;
	}

	public void setIdi(Boolean idi) {
		this.idi = idi;
	}

	public Boolean getIdj() {
		return idj;
	}

	public void setIdj(Boolean idj) {
		this.idj = idj;
	}

	public Timestamp getIdaa() {
		return idaa;
	}

	public void setIdaa(Timestamp idaa) {
		this.idaa = idaa;
	}

	public String getIdab() {
		return idab;
	}

	public void setIdab(String idab) {
		this.idab = idab;
	}

	public Boolean getIdac() {
		return idac;
	}

	public void setIdac(Boolean idac) {
		this.idac = idac;
	}

	public String getIdad() {
		return idad;
	}

	public void setIdad(String idad) {
		this.idad = idad;
	}

	public String getHcafa() {
		return hcafa;
	}

	public void setHcafa(String hcafa) {
		this.hcafa = hcafa;
	}

	public Timestamp getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getHoraAuditoria() {
		return horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public String getEbc() {
		return ebc;
	}

	public void setEbc(String ebc) {
		this.ebc = ebc;
	}

	public String getEbcf() {
		return ebcf;
	}

	public void setEbcf(String ebcf) {
		this.ebcf = ebcf;
	}

	public String getEbd() {
		return ebd;
	}

	public void setEbd(String ebd) {
		this.ebd = ebd;
	}

	public String getEbe() {
		return ebe;
	}

	public void setEbe(String ebe) {
		this.ebe = ebe;
	}

	public String getEbeg() {
		return ebeg;
	}

	public void setEbeg(String ebeg) {
		this.ebeg = ebeg;
	}

	public String getEbf() {
		return ebf;
	}

	public void setEbf(String ebf) {
		this.ebf = ebf;
	}

	public String getEbg() {
		return ebg;
	}

	public void setEbg(String ebg) {
		this.ebg = ebg;
	}

	public String getEbge() {
		return ebge;
	}

	public void setEbge(String ebge) {
		this.ebge = ebge;
	}

	public Set<PlanAccion> getPlanes() {
		return planes;
	}

	public void setPlanes(Set<PlanAccion> planes) {
		this.planes = planes;
	}

	public Timestamp getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Timestamp fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	public String getOrdenamientos() {
		return ordenamientos;
	}

	public void setOrdenamientos(String ordenamientos) {
		this.ordenamientos = ordenamientos;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public byte[] getImagenA() {
		return imagenA;
	}

	public void setImagenA(byte[] imagenA) {
		this.imagenA = imagenA;
	}

	public byte[] getImagenB() {
		return imagenB;
	}

	public void setImagenB(byte[] imagenB) {
		this.imagenB = imagenB;
	}

	public byte[] getImagenC() {
		return imagenC;
	}

	public void setImagenC(byte[] imagenC) {
		this.imagenC = imagenC;
	}

	public byte[] getImagenD() {
		return imagenD;
	}

	public void setImagenD(byte[] imagenD) {
		this.imagenD = imagenD;
	}

	public byte[] getImagenE() {
		return imagenE;
	}

	public void setImagenE(byte[] imagenE) {
		this.imagenE = imagenE;
	}

	public String getObsImagenA() {
		return obsImagenA;
	}

	public void setObsImagenA(String obsImagenA) {
		this.obsImagenA = obsImagenA;
	}

	public String getObsImagenB() {
		return obsImagenB;
	}

	public void setObsImagenB(String obsImagenB) {
		this.obsImagenB = obsImagenB;
	}

	public String getObsImagenC() {
		return obsImagenC;
	}

	public void setObsImagenC(String obsImagenC) {
		this.obsImagenC = obsImagenC;
	}

	public String getObsImagenD() {
		return obsImagenD;
	}

	public void setObsImagenD(String obsImagenD) {
		this.obsImagenD = obsImagenD;
	}

	public String getObsImagenE() {
		return obsImagenE;
	}

	public void setObsImagenE(String obsImagenE) {
		this.obsImagenE = obsImagenE;
	}

	public String getSeleccionadaA() {
		return seleccionadaA;
	}

	public void setSeleccionadaA(String seleccionadaA) {
		this.seleccionadaA = seleccionadaA;
	}

	public String getSeleccionadaB() {
		return seleccionadaB;
	}

	public void setSeleccionadaB(String seleccionadaB) {
		this.seleccionadaB = seleccionadaB;
	}

	
}
