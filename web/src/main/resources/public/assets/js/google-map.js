var autocomplete;
function initMap(){
    autocomplete = new google.maps.places.Autocomplete(document.getElementById('autocomplete'), {
    language: 'ua',
    componentRestrictions: {country: 'ua'}
});
}
