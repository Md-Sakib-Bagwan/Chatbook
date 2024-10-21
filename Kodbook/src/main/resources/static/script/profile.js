// Wait until the DOM is fully loaded
document.addEventListener('DOMContentLoaded', function () {
    // Select all three-dot menus
    const dotMenus = document.querySelectorAll('.three-dot-menu');

    dotMenus.forEach(menu => {
        const dots = menu.querySelector('.dots');
        const dropdown = menu.querySelector('.dropdown-menu');

        // Toggle the visibility of the dropdown menu on click
        dots.addEventListener('click', function (event) {
            event.stopPropagation(); // Prevent the event from bubbling up
            closeAllDropdowns(); // Close other open dropdowns
            dropdown.classList.toggle('show');
        });
    });

    // Close the dropdown if clicking outside of it
    document.addEventListener('click', function () {
        closeAllDropdowns();
    });

    // Function to close all dropdowns
    function closeAllDropdowns() {
        const dropdowns = document.querySelectorAll('.dropdown-menu');
        dropdowns.forEach(dropdown => {
            dropdown.classList.remove('show');
        });
    }
});
