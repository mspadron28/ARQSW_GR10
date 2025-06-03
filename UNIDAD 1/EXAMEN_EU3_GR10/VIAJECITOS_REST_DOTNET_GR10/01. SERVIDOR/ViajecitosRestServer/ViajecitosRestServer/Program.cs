using Microsoft.EntityFrameworkCore;
using ViajecitosRestServer.Data;
using ViajecitosRestServer.Services;
using Microsoft.OpenApi.Models;
using System.Text.Json.Serialization; // Necesario para ReferenceHandler

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllers()
    .AddJsonOptions(options =>
    {
        options.JsonSerializerOptions.ReferenceHandler = ReferenceHandler.Preserve; // Maneja ciclos de referencia
        options.JsonSerializerOptions.WriteIndented = true; // Opcional, para JSON legible
    });

// Configure logging
builder.Services.AddLogging(logging =>
{
    logging.AddConsole();
    logging.SetMinimumLevel(LogLevel.Information);
});

// Configure DbContext with SQL Server and enable detailed logging
builder.Services.AddDbContext<VuelosDbContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection"))
           .LogTo(Console.WriteLine, LogLevel.Information) // Log SQL commands and errors to the console
           .EnableSensitiveDataLogging(true) // Show parameter values in logs (use only in development)
           .EnableDetailedErrors(true)); // Enable detailed error messages

// Register the service
builder.Services.AddScoped<ViajecitosService>();

// Register Swagger
builder.Services.AddSwaggerGen(c =>
{
    c.SwaggerDoc("v1", new OpenApiInfo { Title = "Viajecitos REST API", Version = "v1" });
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(c =>
    {
        c.SwaggerEndpoint("/swagger/v1/swagger.json", "Viajecitos REST API V1");
        c.RoutePrefix = string.Empty; // Hace que Swagger est� en la ra�z (/)
    });
}

app.UseHttpsRedirection();

// app.UseAuthorization(); // Comentado porque no parece necesario en este momento

app.MapControllers();

app.Run();