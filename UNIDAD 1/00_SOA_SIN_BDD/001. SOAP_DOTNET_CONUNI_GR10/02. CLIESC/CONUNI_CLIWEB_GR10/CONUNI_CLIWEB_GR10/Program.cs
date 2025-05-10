var builder = WebApplication.CreateBuilder(args);

// Agregar servicios de MVC
builder.Services.AddControllersWithViews();

// Agregar soporte para sesiones
builder.Services.AddDistributedMemoryCache(); // Almacenamiento en memoria para sesiones
builder.Services.AddSession(options =>
{
    options.IdleTimeout = TimeSpan.FromMinutes(30); // Tiempo de expiración de la sesión
    options.Cookie.HttpOnly = true; // La cookie de sesión no será accesible desde JavaScript
    options.Cookie.IsEssential = true; // La cookie es esencial para el funcionamiento (cumple con GDPR)
});

var app = builder.Build();

// Configurar el pipeline de HTTP
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();
app.UseSession();
app.UseAuthorization();



// Configurar la ruta predeterminada para MVC
app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Conversion}/{action=Login}/{id?}");

app.Run();