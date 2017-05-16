package in.co.snapqa.clientapp0903;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ToggleButton;
import java.util.ArrayList;
import java.util.Locale;

import in.co.snapqa.clientapp0903.interfaces.API;
import in.co.snapqa.clientapp0903.models.SubjectAddRequest;
import in.co.snapqa.clientapp0903.models.SubjectAddResponse;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.R.attr.typeface;

public class SelectSubjectActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    ToggleButton mechanicalSubjects, civilSubjects, electricalSubjects, mathSubjects,physicsSubjects, chemistrySubjects, softwareSubjects, economicsSubjects, miscSubjects;
    ArrayList<String> subjectList;
    LinearLayout mechSubjectsLayout, civilSubjectsLayout, electricalSubjectsLayout, mathSubjectLayout, physicsSubjectLayout, chemistrySubjectLayout, softwareSubjectLayout, economicsSubjectLayout, miscSubjectLayout;
    ToggleButton thermodynamics, heatTransfer, fluidMechanics, thermoFluids, compressibleFluidFlow, cfd, internalCombustion, turbomachines, statics, dynamics, strengthOfMaterial, theoryOfMachines, machineDesign, vibrations;
    ToggleButton systemDynamics, designOfMachineElements, manufacturingProcess, materialScience, compositeMaterial, measurement, controlSystem, instrumentation, finiteElementAnalysis, engineeringDrawing, solidWorks;
    ToggleButton ansys, autocad, matlabMechanical, robotics;
    ToggleButton staticsCivil, dynamicsCivil, strengthOfMaterialCivil, structuralAnalysis, environmentalEngineering, hydraulics, soilMechanics, surveying, fluidMechanicsCivil, engineeringDrawingCivil, solidWorksCivil, transportationEngineering;
    ToggleButton basicEC, circuits, measurementElectrical, controlSystemElectrical, embeddedSystem, electrostatics, powerSystems, electricalEngineering, roboticsElectrical, matlabElectrical;
    ToggleButton linearAlgebra, precalculus, calculus1, calculus2, calculus3, differentialEquations, numericalMethods, sequenceAndSeries, vectors, probability, statistics, vba, complexMaths;
    ToggleButton mechanics, physics, electrostaticsPhysics, soundAndWaves, optics, mmodernPhysics, gravitation;
    ToggleButton inorganicChemistry, organicChemistry, physicalChemistry, generalChemistry;
    ToggleButton matlab, cpp, java, autocadSoftware, ansysSoftware, solidWorksSoftware, vbaSoftware, visualStudio, appDevelopment;
    ToggleButton finance, accountancy, engineeringEconomics, probabilityEconomics, statisticsEconomics, operationResearch;
    ToggleButton roboticsMisc, philosophy, humanities, essayWriting, alternativeEnergy, powderMetaallurgy, geology, geophysics, operationResearchMisc, engineeringCosttAnalysis;
    ScrollView scrollView;

    ProgressDialog progressDialog;

    Button selectSubject;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Key = "key";



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/opensanslight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        getSupportActionBar().setTitle("Choose Your Subjects");

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/opensanssemibold.ttf");

        mechanicalSubjects = (ToggleButton) findViewById(R.id.sub1toggle);
        civilSubjects = (ToggleButton) findViewById(R.id.civil_subject_toggle);
        electricalSubjects = (ToggleButton) findViewById(R.id.electrical_and_electronic_subject_toggle);
        mathSubjects = (ToggleButton) findViewById(R.id.mathematics_subject_toggle);
        physicsSubjects = (ToggleButton) findViewById(R.id.physics_subject_toggle);
        chemistrySubjects = (ToggleButton) findViewById(R.id.chemistry_subject_toggle);
        softwareSubjects = (ToggleButton) findViewById(R.id.software_works_subject_toggle);
        economicsSubjects = (ToggleButton) findViewById(R.id.economics_subject_toggle);
        miscSubjects = (ToggleButton) findViewById(R.id.misc_subject_toggle);

        mechanicalSubjects.setTypeface(font);
        civilSubjects.setTypeface(font);
        electricalSubjects.setTypeface(font);
        mathSubjects.setTypeface(font);
        physicsSubjects.setTypeface(font);
        chemistrySubjects.setTypeface(font);
        softwareSubjects.setTypeface(font);
        economicsSubjects.setTypeface(font);
        miscSubjects.setTypeface(font);


        selectSubject = (Button) findViewById(R.id.confirm_subject);

        scrollView = (ScrollView) findViewById(R.id.select_subject_scrollview);

        mechSubjectsLayout = (LinearLayout) findViewById(R.id.mech_subjects);
        mechSubjectsLayout.setVisibility(LinearLayout.GONE);

        civilSubjectsLayout = (LinearLayout) findViewById(R.id.civil_subjects);
        civilSubjectsLayout.setVisibility(LinearLayout.GONE);

        electricalSubjectsLayout = (LinearLayout) findViewById(R.id.electrical_and_electronic_subjects);
        electricalSubjectsLayout.setVisibility(LinearLayout.GONE);

        mathSubjectLayout = (LinearLayout) findViewById(R.id.math_subjects);
        mathSubjectLayout.setVisibility(LinearLayout.GONE);

        physicsSubjectLayout = (LinearLayout) findViewById(R.id.physics_subjects);
        physicsSubjectLayout.setVisibility(LinearLayout.GONE);

        chemistrySubjectLayout = (LinearLayout) findViewById(R.id.chemistry_subjects);
        chemistrySubjectLayout.setVisibility(LinearLayout.GONE);

        softwareSubjectLayout = (LinearLayout) findViewById(R.id.software_works_subjects);
        softwareSubjectLayout.setVisibility(LinearLayout.GONE);

        economicsSubjectLayout = (LinearLayout) findViewById(R.id.meconomics_subjects);
        economicsSubjectLayout.setVisibility(LinearLayout.GONE);

        miscSubjectLayout = (LinearLayout) findViewById(R.id.misc_subjects);
        miscSubjectLayout.setVisibility(LinearLayout.GONE);

        thermodynamics = (ToggleButton) findViewById(R.id.select_subject_thermodynamics);
        heatTransfer = (ToggleButton) findViewById(R.id.select_subject_heat_transfer);
        fluidMechanics = (ToggleButton) findViewById(R.id.select_subject_fluid);
        thermoFluids = (ToggleButton) findViewById(R.id.select_subject_thermo_fluids);
        compressibleFluidFlow = (ToggleButton) findViewById(R.id.select_subject_compressible_fluid);
        cfd = (ToggleButton) findViewById(R.id.select_subject_computational_fluid_dynamics);
        internalCombustion = (ToggleButton) findViewById(R.id.select_subject_internal_combustion);
        turbomachines = (ToggleButton) findViewById(R.id.select_subject_turbomachines);
        statics = (ToggleButton) findViewById(R.id.select_subject_statics);
        dynamics = (ToggleButton) findViewById(R.id.select_subject_dynamics);
        strengthOfMaterial = (ToggleButton) findViewById(R.id.select_subject_som);
        theoryOfMachines = (ToggleButton) findViewById(R.id.select_subject_theory_of_machines);
        machineDesign = (ToggleButton) findViewById(R.id.select_subject_machine_design);
        vibrations = (ToggleButton) findViewById(R.id.select_subject_vibrations);
        systemDynamics = (ToggleButton) findViewById(R.id.select_subject_system_dynamics);
        designOfMachineElements = (ToggleButton) findViewById(R.id.select_subject_design_of_machine);
        manufacturingProcess = (ToggleButton) findViewById(R.id.select_subject_manufacturing_process);
        materialScience = (ToggleButton) findViewById(R.id.select_subject_material_science);
        compositeMaterial = (ToggleButton) findViewById(R.id.select_subject_composite_material);
        measurement = (ToggleButton) findViewById(R.id.select_subject_measurements_mechanical);
        controlSystem = (ToggleButton) findViewById(R.id.select_subject_control_system_mechanical);
        instrumentation = (ToggleButton) findViewById(R.id.select_subject_instrumentation_mechanical);
        finiteElementAnalysis = (ToggleButton) findViewById(R.id.select_subject_finite_element_analysis);
        engineeringDrawing = (ToggleButton) findViewById(R.id.select_subject_engineering_drawing);
        solidWorks = (ToggleButton) findViewById(R.id.select_subject_solid_works);
        ansys = (ToggleButton) findViewById(R.id.select_subject_ansys);
        autocad = (ToggleButton) findViewById(R.id.select_subject_autocad);
        matlabMechanical = (ToggleButton) findViewById(R.id.select_subject_matlab);
        robotics = (ToggleButton) findViewById(R.id.select_subject_robotics);
        staticsCivil = (ToggleButton) findViewById(R.id.select_subject_statics_civil);
        dynamicsCivil = (ToggleButton) findViewById(R.id.select_subject_dynamics_civil);
        strengthOfMaterialCivil = (ToggleButton) findViewById(R.id.select_subject_strength_of_material_civil);
        structuralAnalysis = (ToggleButton) findViewById(R.id.select_subject_structural_analysis);
        environmentalEngineering = (ToggleButton) findViewById(R.id.select_subject_environmental_engineering);
        hydraulics = (ToggleButton) findViewById(R.id.select_subject_hydraulics);
        soilMechanics = (ToggleButton) findViewById(R.id.select_subject_soil_mechanics);
        surveying = (ToggleButton) findViewById(R.id.select_subject_surveying);
        fluidMechanicsCivil = (ToggleButton) findViewById(R.id.select_subject_fluid_mechanics_civil);
        engineeringDrawingCivil = (ToggleButton) findViewById(R.id.select_subject_engineering_drawing_civil);
        solidWorksCivil = (ToggleButton) findViewById(R.id.select_subject_solid_works_civil);
        transportationEngineering = (ToggleButton) findViewById(R.id.select_subject_transportation_engineering);
        basicEC = (ToggleButton) findViewById(R.id.select_subject_basic_ec);
        circuits = (ToggleButton) findViewById(R.id.select_subject_circuits);
        measurementElectrical = (ToggleButton) findViewById(R.id.select_subject_measurements_electrical);
        controlSystemElectrical = (ToggleButton) findViewById(R.id.select_subject_control_system_electrical);
        embeddedSystem = (ToggleButton) findViewById(R.id.select_subject_embedded_system);
        electrostatics = (ToggleButton) findViewById(R.id.select_subject_electrostatics);
        powerSystems = (ToggleButton) findViewById(R.id.select_subject_power_system);
        electricalEngineering = (ToggleButton) findViewById(R.id.select_subject_electrical_engineering);
        roboticsElectrical = (ToggleButton) findViewById(R.id.select_subject_robotics_electrical);
        matlabElectrical = (ToggleButton) findViewById(R.id.select_subject_matlab_electrical);
        linearAlgebra = (ToggleButton) findViewById(R.id.select_subject_linear_algebra);
        precalculus = (ToggleButton) findViewById(R.id.select_subject_precalculus);
        calculus1 = (ToggleButton) findViewById(R.id.select_subject_calculus1);
        calculus2 = (ToggleButton) findViewById(R.id.select_subject_calculus2);
        calculus3 = (ToggleButton) findViewById(R.id.select_subject_calculus3);
        differentialEquations = (ToggleButton) findViewById(R.id.select_subject_differential_equations);
        numericalMethods = (ToggleButton) findViewById(R.id.select_subject_numerical_method);
        sequenceAndSeries = (ToggleButton) findViewById(R.id.select_subject_sequence_and_series);
        vectors = (ToggleButton) findViewById(R.id.select_subject_vectors);
        probability = (ToggleButton) findViewById(R.id.select_subject_probability);
        statistics = (ToggleButton) findViewById(R.id.select_subject_statistics);
        vba = (ToggleButton) findViewById(R.id.select_subject_vba);
        complexMaths = (ToggleButton) findViewById(R.id.select_subject_complex_maths);
        mechanics = (ToggleButton) findViewById(R.id.select_subject_mechanics_physics);
        physics = (ToggleButton) findViewById(R.id.select_subject_physics);
        electrostaticsPhysics = (ToggleButton) findViewById(R.id.select_subject_electrostatics_physics);
        soundAndWaves = (ToggleButton) findViewById(R.id.select_subject_sound_and_waves);
        optics = (ToggleButton) findViewById(R.id.select_subject_optics);
        mmodernPhysics = (ToggleButton) findViewById(R.id.select_subject_modern_phisics);
        gravitation = (ToggleButton) findViewById(R.id.select_subject_gravitation);
        inorganicChemistry = (ToggleButton) findViewById(R.id.select_subject_inorganic);
        organicChemistry = (ToggleButton) findViewById(R.id.select_subject_organic);
        physicalChemistry = (ToggleButton) findViewById(R.id.select_subject_physical);
        generalChemistry = (ToggleButton) findViewById(R.id.select_subject_general_chemistry);
        matlab = (ToggleButton) findViewById(R.id.select_subject_matlab_software);
        cpp = (ToggleButton) findViewById(R.id.select_subject_cpp);
        java = (ToggleButton) findViewById(R.id.select_subject_java);
        autocadSoftware = (ToggleButton) findViewById(R.id.select_subject_autocad_software);
        ansysSoftware = (ToggleButton) findViewById(R.id.select_subject_ansys_software);
        solidWorksSoftware = (ToggleButton) findViewById(R.id.select_subject_solid_works_software);
        vbaSoftware = (ToggleButton) findViewById(R.id.select_subject_vba_software);
        visualStudio = (ToggleButton) findViewById(R.id.select_subject_visual_studio);
        appDevelopment = (ToggleButton) findViewById(R.id.select_subject_development);
        finance = (ToggleButton) findViewById(R.id.select_subject_finance);
        accountancy = (ToggleButton) findViewById(R.id.select_subject_accountancy);
        engineeringEconomics = (ToggleButton) findViewById(R.id.select_subject_engineering_economics);
        probabilityEconomics = (ToggleButton) findViewById(R.id.select_subject_probability_economics);
        statisticsEconomics = (ToggleButton) findViewById(R.id.select_subject_statistics_economics);
        operationResearch = (ToggleButton) findViewById(R.id.select_subject_operations_research);
        roboticsMisc = (ToggleButton) findViewById(R.id.select_subject_robotics_misc);
        philosophy = (ToggleButton) findViewById(R.id.select_subject_philosophy);
        humanities = (ToggleButton) findViewById(R.id.select_subject_humanities);
        essayWriting = (ToggleButton) findViewById(R.id.select_subject_essay_writing);
        alternativeEnergy = (ToggleButton) findViewById(R.id.select_subject_alternative_energy);
        powderMetaallurgy = (ToggleButton) findViewById(R.id.select_subject_powder_metallurgy);
        geology = (ToggleButton) findViewById(R.id.select_subject_geology);
        geophysics = (ToggleButton) findViewById(R.id.select_subject_geophysics);
        operationResearchMisc = (ToggleButton) findViewById(R.id.select_subject_operations_research_misc);
        engineeringCosttAnalysis = (ToggleButton) findViewById(R.id.select_subject_engineering_cost_analysis);

        thermodynamics.setOnCheckedChangeListener(this);
        heatTransfer.setOnCheckedChangeListener(this);
        fluidMechanics.setOnCheckedChangeListener(this);
        thermoFluids.setOnCheckedChangeListener(this);
        compressibleFluidFlow.setOnCheckedChangeListener(this);
        cfd.setOnCheckedChangeListener(this);
        internalCombustion.setOnCheckedChangeListener(this);
        turbomachines.setOnCheckedChangeListener(this);
        statics.setOnCheckedChangeListener(this);
        dynamics.setOnCheckedChangeListener(this);
        strengthOfMaterial.setOnCheckedChangeListener(this);
        theoryOfMachines.setOnCheckedChangeListener(this);
        machineDesign.setOnCheckedChangeListener(this);
        vibrations.setOnCheckedChangeListener(this);
        systemDynamics.setOnCheckedChangeListener(this);
        designOfMachineElements.setOnCheckedChangeListener(this);
        manufacturingProcess.setOnCheckedChangeListener(this);
        materialScience.setOnCheckedChangeListener(this);
        compositeMaterial.setOnCheckedChangeListener(this);
        measurement.setOnCheckedChangeListener(this);
        controlSystem.setOnCheckedChangeListener(this);
        instrumentation.setOnCheckedChangeListener(this);
        finiteElementAnalysis.setOnCheckedChangeListener(this);
        engineeringDrawing.setOnCheckedChangeListener(this);
        solidWorks.setOnCheckedChangeListener(this);
        ansys.setOnCheckedChangeListener(this);
        autocad.setOnCheckedChangeListener(this);
        matlabMechanical.setOnCheckedChangeListener(this);
        robotics.setOnCheckedChangeListener(this);
        staticsCivil.setOnCheckedChangeListener(this);
        dynamicsCivil.setOnCheckedChangeListener(this);
        strengthOfMaterialCivil.setOnCheckedChangeListener(this);
        structuralAnalysis.setOnCheckedChangeListener(this);
        environmentalEngineering.setOnCheckedChangeListener(this);
        hydraulics.setOnCheckedChangeListener(this);
        soilMechanics.setOnCheckedChangeListener(this);
        surveying.setOnCheckedChangeListener(this);
        fluidMechanicsCivil.setOnCheckedChangeListener(this);
        engineeringDrawingCivil.setOnCheckedChangeListener(this);
        solidWorksCivil.setOnCheckedChangeListener(this);
        transportationEngineering.setOnCheckedChangeListener(this);
        basicEC.setOnCheckedChangeListener(this);
        circuits.setOnCheckedChangeListener(this);
        embeddedSystem.setOnCheckedChangeListener(this);
        electrostatics.setOnCheckedChangeListener(this);
        powerSystems.setOnCheckedChangeListener(this);
        electricalEngineering.setOnCheckedChangeListener(this);
        roboticsElectrical.setOnCheckedChangeListener(this);
        matlabElectrical.setOnCheckedChangeListener(this);
        linearAlgebra.setOnCheckedChangeListener(this);
        precalculus.setOnCheckedChangeListener(this);
        calculus1.setOnCheckedChangeListener(this);
        calculus2.setOnCheckedChangeListener(this);
        calculus3.setOnCheckedChangeListener(this);
        differentialEquations.setOnCheckedChangeListener(this);
        numericalMethods.setOnCheckedChangeListener(this);
        sequenceAndSeries.setOnCheckedChangeListener(this);
        vectors.setOnCheckedChangeListener(this);
        probability.setOnCheckedChangeListener(this);
        statistics.setOnCheckedChangeListener(this);
        vba.setOnCheckedChangeListener(this);
        complexMaths.setOnCheckedChangeListener(this);
        mechanics.setOnCheckedChangeListener(this);
        physics.setOnCheckedChangeListener(this);
        electrostaticsPhysics.setOnCheckedChangeListener(this);
        soundAndWaves.setOnCheckedChangeListener(this);
        optics.setOnCheckedChangeListener(this);
        mmodernPhysics.setOnCheckedChangeListener(this);
        gravitation.setOnCheckedChangeListener(this);
        inorganicChemistry.setOnCheckedChangeListener(this);
        organicChemistry.setOnCheckedChangeListener(this);
        physicalChemistry.setOnCheckedChangeListener(this);
        generalChemistry.setOnCheckedChangeListener(this);
        cpp.setOnCheckedChangeListener(this);
        java.setOnCheckedChangeListener(this);
        autocadSoftware.setOnCheckedChangeListener(this);
        ansysSoftware.setOnCheckedChangeListener(this);
        solidWorksSoftware.setOnCheckedChangeListener(this);
        vbaSoftware.setOnCheckedChangeListener(this);
        visualStudio.setOnCheckedChangeListener(this);
        appDevelopment.setOnCheckedChangeListener(this);
        finance.setOnCheckedChangeListener(this);
        accountancy.setOnCheckedChangeListener(this);
        engineeringEconomics.setOnCheckedChangeListener(this);
        probabilityEconomics.setOnCheckedChangeListener(this);
        statisticsEconomics.setOnCheckedChangeListener(this);
        operationResearch.setOnCheckedChangeListener(this);
        roboticsMisc.setOnCheckedChangeListener(this);
        philosophy.setOnCheckedChangeListener(this);
        humanities.setOnCheckedChangeListener(this);
        essayWriting.setOnCheckedChangeListener(this);
        alternativeEnergy.setOnCheckedChangeListener(this);
        powderMetaallurgy.setOnCheckedChangeListener(this);
        geology.setOnCheckedChangeListener(this);
        geophysics.setOnCheckedChangeListener(this);
        operationResearchMisc.setOnCheckedChangeListener(this);
        engineeringCosttAnalysis.setOnCheckedChangeListener(this);
        measurementElectrical.setOnCheckedChangeListener(this);
        controlSystemElectrical.setOnCheckedChangeListener(this);
        matlab.setOnCheckedChangeListener(this);



        // On Click Listeners
        mechanicalSubjects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chemistrySubjectLayout.setVisibility(LinearLayout.GONE);
                    softwareSubjectLayout.setVisibility(LinearLayout.GONE);
                    mechSubjectsLayout.setVisibility(LinearLayout.VISIBLE);
                    civilSubjectsLayout.setVisibility(LinearLayout.GONE);
                    electricalSubjectsLayout.setVisibility(LinearLayout.GONE);
                    mathSubjectLayout.setVisibility(LinearLayout.GONE);
                    physicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    economicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    miscSubjectLayout.setVisibility(LinearLayout.GONE);
                    scrollView.scrollTo(0, mechanicalSubjects.getTop());
                }else{
                    mechSubjectsLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });

        civilSubjects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chemistrySubjectLayout.setVisibility(LinearLayout.GONE);
                    softwareSubjectLayout.setVisibility(LinearLayout.GONE);
                    mechSubjectsLayout.setVisibility(LinearLayout.GONE);
                    civilSubjectsLayout.setVisibility(LinearLayout.VISIBLE);
                    electricalSubjectsLayout.setVisibility(LinearLayout.GONE);
                    mathSubjectLayout.setVisibility(LinearLayout.GONE);
                    physicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    economicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    miscSubjectLayout.setVisibility(LinearLayout.GONE);
                    scrollView.scrollTo(0,mechanicalSubjects.getBaseline());
                }else{
                    civilSubjectsLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });

        electricalSubjects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chemistrySubjectLayout.setVisibility(LinearLayout.GONE);
                    softwareSubjectLayout.setVisibility(LinearLayout.GONE);
                    mechSubjectsLayout.setVisibility(LinearLayout.GONE);
                    civilSubjectsLayout.setVisibility(LinearLayout.GONE);
                    electricalSubjectsLayout.setVisibility(LinearLayout.VISIBLE);
                    mathSubjectLayout.setVisibility(LinearLayout.GONE);
                    physicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    economicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    miscSubjectLayout.setVisibility(LinearLayout.GONE);
                    scrollView.scrollTo(0, civilSubjects.getBaseline());
                }else{
                    electricalSubjectsLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });

        mathSubjects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chemistrySubjectLayout.setVisibility(LinearLayout.GONE);
                    softwareSubjectLayout.setVisibility(LinearLayout.GONE);
                    mechSubjectsLayout.setVisibility(LinearLayout.GONE);
                    civilSubjectsLayout.setVisibility(LinearLayout.GONE);
                    electricalSubjectsLayout.setVisibility(LinearLayout.GONE);
                    mathSubjectLayout.setVisibility(LinearLayout.VISIBLE);
                    physicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    economicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    miscSubjectLayout.setVisibility(LinearLayout.GONE);
                    scrollView.scrollTo(0, electricalSubjects.getBaseline());
                }else{
                    mathSubjectLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });
        physicsSubjects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chemistrySubjectLayout.setVisibility(LinearLayout.GONE);
                    softwareSubjectLayout.setVisibility(LinearLayout.GONE);
                    mechSubjectsLayout.setVisibility(LinearLayout.GONE);
                    civilSubjectsLayout.setVisibility(LinearLayout.GONE);
                    electricalSubjectsLayout.setVisibility(LinearLayout.GONE);
                    mathSubjectLayout.setVisibility(LinearLayout.GONE);
                    physicsSubjectLayout.setVisibility(LinearLayout.VISIBLE);
                    economicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    miscSubjectLayout.setVisibility(LinearLayout.GONE);
                    scrollView.scrollTo(0, mathSubjects.getBaseline());
                }else{
                    physicsSubjectLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });
        chemistrySubjects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chemistrySubjectLayout.setVisibility(LinearLayout.VISIBLE);
                    softwareSubjectLayout.setVisibility(LinearLayout.GONE);
                    mechSubjectsLayout.setVisibility(LinearLayout.GONE);
                    civilSubjectsLayout.setVisibility(LinearLayout.GONE);
                    electricalSubjectsLayout.setVisibility(LinearLayout.GONE);
                    mathSubjectLayout.setVisibility(LinearLayout.GONE);
                    physicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    economicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    miscSubjectLayout.setVisibility(LinearLayout.GONE);
                    scrollView.scrollTo(0, physicsSubjects.getBaseline());
                }else{
                    chemistrySubjectLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });

        softwareSubjects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chemistrySubjectLayout.setVisibility(LinearLayout.GONE);
                    softwareSubjectLayout.setVisibility(LinearLayout.VISIBLE);
                    mechSubjectsLayout.setVisibility(LinearLayout.GONE);
                    civilSubjectsLayout.setVisibility(LinearLayout.GONE);
                    electricalSubjectsLayout.setVisibility(LinearLayout.GONE);
                    mathSubjectLayout.setVisibility(LinearLayout.GONE);
                    physicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    economicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    miscSubjectLayout.setVisibility(LinearLayout.GONE);
                    scrollView.scrollTo(0, chemistrySubjects.getBaseline());
                }else{
                    softwareSubjectLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });

        economicsSubjects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chemistrySubjectLayout.setVisibility(LinearLayout.GONE);
                    softwareSubjectLayout.setVisibility(LinearLayout.GONE);
                    mechSubjectsLayout.setVisibility(LinearLayout.GONE);
                    civilSubjectsLayout.setVisibility(LinearLayout.GONE);
                    electricalSubjectsLayout.setVisibility(LinearLayout.GONE);
                    mathSubjectLayout.setVisibility(LinearLayout.GONE);
                    physicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    economicsSubjectLayout.setVisibility(LinearLayout.VISIBLE);
                    miscSubjectLayout.setVisibility(LinearLayout.GONE);
                    scrollView.scrollTo(0, softwareSubjects.getBaseline());
                }else{
                    economicsSubjectLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });

        miscSubjects.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chemistrySubjectLayout.setVisibility(LinearLayout.GONE);
                    softwareSubjectLayout.setVisibility(LinearLayout.GONE);
                    mechSubjectsLayout.setVisibility(LinearLayout.GONE);
                    civilSubjectsLayout.setVisibility(LinearLayout.GONE);
                    electricalSubjectsLayout.setVisibility(LinearLayout.GONE);
                    mathSubjectLayout.setVisibility(LinearLayout.GONE);
                    physicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    economicsSubjectLayout.setVisibility(LinearLayout.GONE);
                    miscSubjectLayout.setVisibility(LinearLayout.VISIBLE);
                    scrollView.scrollTo(0, economicsSubjects.getBaseline());
                }else{
                    miscSubjectLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });

        subjectList = new ArrayList<String>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final API service = retrofit.create(API.class);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String token = sharedpreferences.getString(Key, "notPresent");

        selectSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(SelectSubjectActivity.this, "Just a sec!", "Saving Your Subjects", true);
                SubjectAddRequest subjectAddRequest = new SubjectAddRequest();
                subjectAddRequest.setToken(token);
                subjectAddRequest.setSubjects(subjectList);

                Call<SubjectAddResponse> subjectAddResponseCall = service.addSubject(subjectAddRequest);

                subjectAddResponseCall.enqueue(new Callback<SubjectAddResponse>() {
                    @Override
                    public void onResponse(Call<SubjectAddResponse> call, Response<SubjectAddResponse> response) {
                        SubjectAddResponse subjectAddResponse = response.body();
                        Log.d("jhfjksf", " lhdkjahj " + subjectAddResponse.getMessage());
                        Log.d("subkect list:   ", "" + subjectList);
                        final String subjects = sharedpreferences.getString("Subjects", "notPresent");
                        if(subjects.equals("Subjects")){
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.remove("Subjects");
                            editor.commit();
                            Intent intent = new Intent(SelectSubjectActivity.this, TutorProfile.class);
                            progressDialog.dismiss();
                            startActivity(intent);
                        }else {
                            Intent mainIntent = new Intent(SelectSubjectActivity.this, PaymentDetailsActivity.class);
                            progressDialog.dismiss();
                            startActivity(mainIntent);
                        }
                    }

                    @Override
                    public void onFailure(Call<SubjectAddResponse> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            Drawable drawable = getResources().getDrawable(R.drawable.button_rounded_corners_blue);
            buttonView.setBackground(drawable);
            buttonView.setTextColor(Color.parseColor("#FFFFFF"));
            subjectList.add(buttonView.getText().toString());

        }else {
            Drawable drawable = getResources().getDrawable(R.drawable.button_rounded_corners);
            buttonView.setBackground(drawable);
            buttonView.setTextColor(Color.parseColor("#03A9F4"));
            for(int i=0; i<subjectList.size(); i++){
                if(buttonView.getText().toString().equals(subjectList.get(i))){

                    subjectList.remove(i);
                    break;

                }
            }
        }


    }

    @Override
    public void onBackPressed() {

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String subjects = sharedpreferences.getString("Subjects", "notPresent");
        Log.d("chjsdgs:  "," + " + subjects);
        if(subjects.equals("Subjects")){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.remove("Subjects");
            editor.commit();
            super.onBackPressed();
        }

    }
}
