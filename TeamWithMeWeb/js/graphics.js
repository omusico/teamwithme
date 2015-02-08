
$(document).ready(function() {

    // Get WebGL container
    container = $('#webGL');

    var canvasHeight = 450;

    // Create renderer and add it to the container
    var renderer = new THREE.WebGLRenderer({ antialias: false });
    renderer.setSize(container.innerWidth(), canvasHeight);
    document.getElementById('webGL').appendChild(renderer.domElement);

    window.addEventListener('resize', onWindowResize, false);

    // Create scene
    var scene = new THREE.Scene();

    // Create camera and add it to the main scene
    var frustumFar = 4000;
    var fov = container.innerWidth() / canvasHeight;
    var camera = new THREE.PerspectiveCamera(45, fov, 1, frustumFar);
    scene.add(camera);

    // Create skybox
    var segments = 16;
    var radius = 20;
    var skyGeometry = new THREE.SphereGeometry(radius, segments, segments);
    var skyMaterial = new THREE.MeshBasicMaterial({ side: THREE.BackSide, color: 0xffffff });
    var skyMesh = new THREE.Mesh(skyGeometry, skyMaterial);
    scene.add(skyMesh);
    
    // Create hemisphere light
    var h_Light = new THREE.HemisphereLight(0xffffff, 0xffffff, 1);
    scene.add(h_Light);

    // Create ambient light
    // var a_light = new THREE.AmbientLight(0xffffff);
    // scene.add(a_light);

    // Create directional light
    var d_light = new THREE.DirectionalLight(0xffffff, 0.3);
    d_light.position.set(0, 1, 1);
    scene.add(d_light);

    var geometry = new THREE.BoxGeometry(4, 4, 4);
    var material = new THREE.MeshLambertMaterial({ color: 0x3399FF });
    var cube = new THREE.Mesh(geometry, material);
    scene.add(cube);

    // Initialize camera position
    camera.position.z = 10;

    run();

    function onWindowResize()
    {
        camera.aspect = container.innerWidth() / canvasHeight;
        camera.updateProjectionMatrix();
        renderer.setSize(container.innerWidth(), canvasHeight);
    }

    function run()
    {
        // Render the scene
        renderer.render(scene, camera);

        cube.rotation.x += 0.005;
        cube.rotation.y += 0.008;

        // Request another frame
        requestAnimationFrame(run);
    }
});
