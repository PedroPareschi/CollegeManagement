package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Class;
import br.com.pedropareschi.collegemanagement.domain.*;
import br.com.pedropareschi.collegemanagement.domain.enums.*;
import br.com.pedropareschi.collegemanagement.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassroomRepository classroomRepository;


    @Autowired
    private ClassTermRepository classTermRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    @Autowired
    private TermRepository termRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CampusRepository campusRepository;

    @Autowired
    private ProgramCourseRepository programCourseRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    public void instantiateTestDatabase() throws ParseException {
        SimpleDateFormat horas = new SimpleDateFormat("HH:mm");
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

        Address unesquinaAddress = new Address(null, "Rua do Sol", "847", null, "Guadalupe", "21665360",
                "Rio de Janeiro", "RJ");
        Address uniFederalAddress = new Address(null, "Rua Elis Regina", "458", null, "Curicica", "22711010",
                "Rio de Janeiro", "RJ");

        College uniesquina = new College(null, "UniEsquina", "47886511000116");
        College uniFederal = new College(null, "UniFederal", "59615582000163");

        Campus uniesquinaRio = new Campus(null, "Rio", unesquinaAddress, uniesquina);
        Campus unirioUrca = new Campus(null, "Curicica", uniFederalAddress, uniFederal);

        uniesquina.setCampuses(Arrays.asList(uniesquinaRio));
        uniFederal.setCampuses(Arrays.asList(unirioUrca));

        Program analise = new Program(null, "Análise e Desenvolvimento de Sistemas", "Departamento de Computação", Shift.MORNING, 5, 10, uniesquinaRio);
        Program sistemas = new Program(null, "Sistema de Informação", "Departamento de Exatas", Shift.AFTERNOOON, 8, 12, unirioUrca);

        uniesquinaRio.setPrograms(Arrays.asList(analise));
        unirioUrca.setPrograms(Arrays.asList(sistemas));

        Address ninaAddress = new Address(null, "Rua Orélia", "867", "apt. 123", "Guadalupe", "21675380",
                "Rio de Janeiro", "RJ");
        Address kaiqueAddress = new Address(null, "Praça Trinas Fox", "230", "apt. 123", "Bangu", "21820060",
                "Rio de Janeiro", "RJ");

        Student nina = new Student(null, "Nina Bianca Farias", "06579791713", "196830102", "pedropareschi15@gmail.com", Sex.FEMININE, false, null, data.parse("14/01/1995"), encoder.encode("123"), 2, CollegeSituation.ACTIVE, analise);
        Student kaique = new Student(null, "Kaique Elias Brito", "53642652778", "280880856", "kaiqueeliasbrito_@doublemoore.com", Sex.MASCULINE, false, null, data.parse("21/03/1957"), encoder.encode("123"), 2, CollegeSituation.PAUSED, sistemas);

        nina.getAddresses().add(ninaAddress);
        nina.getTelephones().add("123456789");
        kaique.getAddresses().add(kaiqueAddress);
        kaique.getTelephones().add("987654321");

        Term secondTerm = new Term(null, 2, Semester.SECOND, 2021, nina);
        Term firstTerm = new Term(null, 1, Semester.FIRST, 2021, nina);
        Term kaiqueFirstTerm = new Term(null, 1, Semester.FIRST, 2021, kaique);
        Term kaiqueSecondTerm = new Term(null, 2, Semester.SECOND, 2021, kaique);

        nina.setTerms(Arrays.asList(firstTerm, secondTerm));
        kaique.setTerms(Arrays.asList(kaiqueFirstTerm, kaiqueSecondTerm));

        Course calculus = new Course(null, "Calculo", 80, 80, "LIMITES DERIVADAS E INTEGRAIS", 20, 5.0);
        Course fac = new Course(null, "Fundamentos de Algoritmo de Computação", 80, 80, "LÓGICA ESTRUTURA DE REPETIÇÃO E DE DECISÃO", 20, 5.0);
        Course calculus2 = new Course(null, "Calculo 2", 80, 80, "DERIVADAS E INTEGRAIS MULTIPLAS", 20, 5.0);

        ProgramCourse facAnalise = new ProgramCourse(analise, fac, 1);
        ProgramCourse calculoSistema = new ProgramCourse(sistemas, calculus, 1);
        ProgramCourse calculo2Sistema = new ProgramCourse(sistemas, calculus2, 2);

        analise.getCourses().add(facAnalise);
        sistemas.getCourses().addAll(Arrays.asList(calculoSistema, calculo2Sistema));

        fac.getPrograms().add(facAnalise);
        calculus.getPrograms().addAll(Arrays.asList(calculoSistema));
        calculus2.getPrograms().add(calculo2Sistema);

        Address mateusAddress = new Address(null, "Rua Pato", "12", "Apt. 432", "Flamengo", "7987900",
                "Rio de Janeiro", "RJ");
        Address leoAddress = new Address(null, "Rua Leão", "98", null, "Tijuca", "987987",
                "Rio de Janeiro", "RJ");

        Teacher mateus = new Teacher(null, "Mateus", "123165464", "798789789", "mateus@email.com", Sex.MASCULINE, false, null, data.parse("21/12/1979"), encoder.encode("123"), "9879879");
        Teacher leo = new Teacher(null, "Léo", "56465440", "154616515", "leo@email.com", Sex.MASCULINE, false, null, data.parse("5/5/1985"), encoder.encode("123"), "6545645646");
        leo.addRole(Role.ADMIN);

        leo.getAddresses().add(leoAddress);
        leo.getTelephones().add("456789123");
        kaique.getAddresses().add(kaiqueAddress);
        kaique.getTelephones().add("789456123");

        Class facManha = new Class(null, Shift.MORNING, Semester.FIRST, 2021, 50, fac, leo);
        Classroom info = new Classroom(null, uniesquinaRio, "FAETERJ", "Primeiro", "120", DayOfWeek.WEDNESDAY, horas.parse("8:40"), horas.parse("12:20"), facManha);
        facManha.setClassrooms(Arrays.asList(info));
        uniesquinaRio.setClassrooms(Arrays.asList(info));

        fac.setClasses(Arrays.asList(facManha));

        Class calculoNoite = new Class(null, Shift.EVENING, Semester.FIRST, 2021, 25, calculus, mateus);
        Classroom sala1 = new Classroom(null, unirioUrca, "Exatas", "Segundo", "245", DayOfWeek.MONDAY, horas.parse("19:00"), horas.parse("21:00"), calculoNoite);
        Classroom sala2 = new Classroom(null, unirioUrca, "Exatas", "Segundo", "245", DayOfWeek.FRIDAY, horas.parse("19:00"), horas.parse("21:00"), calculoNoite);
        calculoNoite.setClassrooms(Arrays.asList(sala1, sala2));

        Class calculoTarde = new Class(null, Shift.AFTERNOOON, Semester.SECOND, 2021, 25, calculus, mateus);
        Classroom sala1Tarde = new Classroom(null, unirioUrca, "Exatas", "Segundo", "245", DayOfWeek.WEDNESDAY, horas.parse("13:00"), horas.parse("18:00"), calculoTarde);
        calculoTarde.setClassrooms(Arrays.asList(sala1Tarde));

        Class calculo2Noite = new Class(null, Shift.EVENING, Semester.SECOND, 2021, 25, calculus2, mateus);
        Classroom sala2Tarde= new Classroom(null, unirioUrca, "Exatas", "Segundo", "245", DayOfWeek.TUESDAY, horas.parse("19:00"), horas.parse("22:00"), calculo2Noite);
        calculo2Noite.setClassrooms(Arrays.asList(sala2Tarde));
        unirioUrca.setClassrooms(Arrays.asList(sala1, sala2, sala1Tarde, sala2Tarde));

        calculus.setClasses(Arrays.asList(calculoTarde, calculoNoite));
        calculus2.setClasses(Arrays.asList(calculo2Noite));

        analise.setStudents(Arrays.asList(nina));
        sistemas.setStudents(Arrays.asList(kaique));

        calculus2.setPrerequisites(Arrays.asList(calculus));
        calculus.setPrerequisiteTo(Arrays.asList(calculus2));

        ClassTerm ninaFAC = new ClassTerm(firstTerm, facManha, ClassSituation.PASSED, 2);
        ninaFAC.setGrades(Arrays.asList(9.3, 9.4));
        facManha.getStudents().add(ninaFAC);
        secondTerm.getClasses().add(ninaFAC);

        ClassTerm kaiqueCalculo = new ClassTerm(kaiqueFirstTerm, calculoNoite, ClassSituation.PASSED, 0);
        kaiqueCalculo.setGrades(Arrays.asList(6.5, 7.0));
        kaiqueFirstTerm.getClasses().add(kaiqueCalculo);
        calculoNoite.getStudents().add(kaiqueCalculo);

        ClassTerm kaiqueCalculo2 = new ClassTerm(kaiqueSecondTerm, calculo2Noite, ClassSituation.ACTIVE, 0);
        kaiqueCalculo2.setGrades(Arrays.asList(6.0));
        calculo2Noite.getStudents().add(kaiqueCalculo2);
        kaiqueSecondTerm.getClasses().add(kaiqueCalculo2);

        collegeRepository.saveAll(Arrays.asList(uniesquina, uniFederal));
        addressRepository.saveAll(Arrays.asList(kaiqueAddress, ninaAddress, leoAddress, mateusAddress, unesquinaAddress, uniFederalAddress));
        campusRepository.saveAll(Arrays.asList(uniesquinaRio, unirioUrca));
        courseRepository.saveAll(Arrays.asList(fac, calculus, calculus2));
        programRepository.saveAll(Arrays.asList(analise, sistemas));
        programCourseRepository.saveAll(Arrays.asList(calculoSistema, calculo2Sistema, facAnalise));
        studentRepository.saveAll(Arrays.asList(nina, kaique));
        termRepository.saveAll(Arrays.asList(firstTerm, secondTerm, kaiqueFirstTerm, kaiqueSecondTerm));
        teacherRepository.saveAll(Arrays.asList(leo, mateus));
        classRepository.saveAll(Arrays.asList(facManha, calculoTarde, calculoNoite, calculo2Noite));
        classroomRepository.saveAll(Arrays.asList(info, sala1, sala2, sala1Tarde, sala2Tarde));
        classTermRepository.saveAll(Arrays.asList(ninaFAC, kaiqueCalculo, kaiqueCalculo2));
    }
}
