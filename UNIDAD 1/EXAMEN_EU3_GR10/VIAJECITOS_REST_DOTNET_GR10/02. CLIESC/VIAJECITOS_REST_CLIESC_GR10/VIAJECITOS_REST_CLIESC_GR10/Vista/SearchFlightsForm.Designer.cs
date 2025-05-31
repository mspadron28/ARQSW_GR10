namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    partial class SearchFlightsForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.headerPanel = new System.Windows.Forms.Panel();
            this.lblUser = new System.Windows.Forms.Label();
            this.lblSubtitle = new System.Windows.Forms.Label();
            this.lblTitle = new System.Windows.Forms.Label();
            this.contentPanel = new System.Windows.Forms.Panel();
            this.formPanel = new System.Windows.Forms.Panel();
            this.linkPanel = new System.Windows.Forms.Panel();
            this.btnRegister = new System.Windows.Forms.Button();
            this.btnLogin = new System.Windows.Forms.Button();
            this.lblError = new System.Windows.Forms.Label();
            this.btnSearch = new System.Windows.Forms.Button();
            this.datePicker = new System.Windows.Forms.DateTimePicker();
            this.lblFecha = new System.Windows.Forms.Label();
            this.cbDestino = new System.Windows.Forms.ComboBox();
            this.lblDestino = new System.Windows.Forms.Label();
            this.cbOrigen = new System.Windows.Forms.ComboBox();
            this.lblOrigen = new System.Windows.Forms.Label();
            this.btnViewPurchases = new System.Windows.Forms.Button();
            this.lblFormTitle = new System.Windows.Forms.Label();
            this.headerPanel.SuspendLayout();
            this.contentPanel.SuspendLayout();
            this.formPanel.SuspendLayout();
            this.linkPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // headerPanel
            // 
            this.headerPanel.Controls.Add(this.lblUser);
            this.headerPanel.Controls.Add(this.lblSubtitle);
            this.headerPanel.Controls.Add(this.lblTitle);
            this.headerPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.headerPanel.Location = new System.Drawing.Point(0, 0);
            this.headerPanel.Name = "headerPanel";
            this.headerPanel.Size = new System.Drawing.Size(800, 120);
            this.headerPanel.TabIndex = 0;
            this.headerPanel.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(163)))), ((int)(((byte)(191)))), ((int)(((byte)(250)))));
            // 
            // lblUser
            // 
            this.lblUser.AutoSize = true;
            this.lblUser.Font = new System.Drawing.Font("Roboto", 10F, System.Drawing.FontStyle.Bold);
            this.lblUser.ForeColor = System.Drawing.Color.White;
            this.lblUser.Location = new System.Drawing.Point(1050, 50);
            this.lblUser.Name = "lblUser";
            this.lblUser.Size = new System.Drawing.Size(60, 17);
            this.lblUser.TabIndex = 2;
            this.lblUser.Text = "Invitado";
            // 
            // lblSubtitle
            // 
            this.lblSubtitle.AutoSize = true;
            this.lblSubtitle.Font = new System.Drawing.Font("Roboto", 12F);
            this.lblSubtitle.ForeColor = System.Drawing.Color.White;
            this.lblSubtitle.Location = new System.Drawing.Point(350, 60);
            this.lblSubtitle.Name = "lblSubtitle";
            this.lblSubtitle.Size = new System.Drawing.Size(400, 20);
            this.lblSubtitle.TabIndex = 1;
            this.lblSubtitle.Text = "Encuentra y compra tus boletos de avión de forma fácil y segura";
            // 
            // lblTitle
            // 
            this.lblTitle.AutoSize = true;
            this.lblTitle.Font = new System.Drawing.Font("Roboto", 22F, System.Drawing.FontStyle.Bold);
            this.lblTitle.ForeColor = System.Drawing.Color.White;
            this.lblTitle.Location = new System.Drawing.Point(350, 20);
            this.lblTitle.Name = "lblTitle";
            this.lblTitle.Size = new System.Drawing.Size(150, 40);
            this.lblTitle.TabIndex = 0;
            this.lblTitle.Text = "Viajecitos SA";
            // 
            // contentPanel
            // 
            this.contentPanel.Controls.Add(this.formPanel);
            this.contentPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.contentPanel.Location = new System.Drawing.Point(0, 120);
            this.contentPanel.Name = "contentPanel";
            this.contentPanel.Size = new System.Drawing.Size(800, 430);
            this.contentPanel.TabIndex = 1;
            this.contentPanel.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(241)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // formPanel
            // 
            this.formPanel.Controls.Add(this.linkPanel);
            this.formPanel.Controls.Add(this.lblError);
            this.formPanel.Controls.Add(this.btnSearch);
            this.formPanel.Controls.Add(this.datePicker);
            this.formPanel.Controls.Add(this.lblFecha);
            this.formPanel.Controls.Add(this.cbDestino);
            this.formPanel.Controls.Add(this.lblDestino);
            this.formPanel.Controls.Add(this.cbOrigen);
            this.formPanel.Controls.Add(this.lblOrigen);
            this.formPanel.Controls.Add(this.btnViewPurchases);
            this.formPanel.Controls.Add(this.lblFormTitle);
            this.formPanel.Location = new System.Drawing.Point(100, 50);
            this.formPanel.Name = "formPanel";
            this.formPanel.Size = new System.Drawing.Size(600, 400);
            this.formPanel.TabIndex = 0;
            this.formPanel.BackColor = System.Drawing.Color.White;
            this.formPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            // 
            // linkPanel
            // 
            this.linkPanel.Controls.Add(this.btnRegister);
            this.linkPanel.Controls.Add(this.btnLogin);
            this.linkPanel.Location = new System.Drawing.Point(0, 320);
            this.linkPanel.Name = "linkPanel";
            this.linkPanel.Size = new System.Drawing.Size(600, 50);
            this.linkPanel.TabIndex = 9;
            // 
            // btnRegister
            // 
            this.btnRegister.AutoSize = true;
            this.btnRegister.FlatAppearance.BorderSize = 0;
            this.btnRegister.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnRegister.Font = new System.Drawing.Font("Roboto", 9F);
            this.btnRegister.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(59)))), ((int)(((byte)(130)))), ((int)(((byte)(246)))));
            this.btnRegister.Location = new System.Drawing.Point(350, 10);
            this.btnRegister.Name = "btnRegister";
            this.btnRegister.Size = new System.Drawing.Size(80, 30);
            this.btnRegister.TabIndex = 1;
            this.btnRegister.Text = "Registrarse";
            this.btnRegister.UseVisualStyleBackColor = true;
            // 
            // btnLogin
            // 
            this.btnLogin.AutoSize = true;
            this.btnLogin.FlatAppearance.BorderSize = 0;
            this.btnLogin.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnLogin.Font = new System.Drawing.Font("Roboto", 9F);
            this.btnLogin.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(59)))), ((int)(((byte)(130)))), ((int)(((byte)(246)))));
            this.btnLogin.Location = new System.Drawing.Point(200, 10);
            this.btnLogin.Name = "btnLogin";
            this.btnLogin.Size = new System.Drawing.Size(100, 30);
            this.btnLogin.TabIndex = 0;
            this.btnLogin.Text = "Iniciar Sesión";
            this.btnLogin.UseVisualStyleBackColor = true;
            // 
            // lblError
            // 
            this.lblError.AutoSize = true;
            this.lblError.Font = new System.Drawing.Font("Roboto", 9F);
            this.lblError.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(220)))), ((int)(((byte)(38)))), ((int)(((byte)(38)))));
            this.lblError.Location = new System.Drawing.Point(50, 280);
            this.lblError.Name = "lblError";
            this.lblError.Size = new System.Drawing.Size(500, 20);
            this.lblError.TabIndex = 8;
            this.lblError.Text = "";
            this.lblError.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // btnSearch
            // 
            this.btnSearch.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnSearch.Font = new System.Drawing.Font("Roboto", 10F, System.Drawing.FontStyle.Bold);
            this.btnSearch.ForeColor = System.Drawing.Color.Black;
            this.btnSearch.Location = new System.Drawing.Point(225, 230);
            this.btnSearch.Name = "btnSearch";
            this.btnSearch.Size = new System.Drawing.Size(150, 40);
            this.btnSearch.TabIndex = 7;
            this.btnSearch.Text = "Buscar";
            this.btnSearch.UseVisualStyleBackColor = false;
            this.btnSearch.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(191)))), ((int)(((byte)(219)))), ((int)(((byte)(254)))));
            // 
            // datePicker
            // 
            this.datePicker.CustomFormat = "yyyy-MM-dd";
            this.datePicker.Font = new System.Drawing.Font("Roboto", 10F);
            this.datePicker.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.datePicker.Location = new System.Drawing.Point(250, 190);
            this.datePicker.Name = "datePicker";
            this.datePicker.Size = new System.Drawing.Size(300, 23);
            this.datePicker.TabIndex = 6;
            // 
            // lblFecha
            // 
            this.lblFecha.AutoSize = true;
            this.lblFecha.Font = new System.Drawing.Font("Roboto", 10F);
            this.lblFecha.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(31)))), ((int)(((byte)(41)))), ((int)(((byte)(55)))));
            this.lblFecha.Location = new System.Drawing.Point(50, 190);
            this.lblFecha.Name = "lblFecha";
            this.lblFecha.Size = new System.Drawing.Size(100, 17);
            this.lblFecha.TabIndex = 5;
            this.lblFecha.Text = "Fecha de Viaje:";
            // 
            // cbDestino
            // 
            this.cbDestino.Font = new System.Drawing.Font("Roboto", 10F);
            this.cbDestino.FormattingEnabled = true;
            this.cbDestino.Items.AddRange(new object[] {
            "Selecciona una ciudad",
            "Bogotá",
            "Medellín",
            "Buenos Aires",
            "Córdoba",
            "Quito",
            "Guayaquil",
            "Cali",
            "Cartagena",
            "Mendoza"});
            this.cbDestino.Location = new System.Drawing.Point(250, 150);
            this.cbDestino.Name = "cbDestino";
            this.cbDestino.Size = new System.Drawing.Size(300, 24);
            this.cbDestino.TabIndex = 4;
            this.cbDestino.Text = "Selecciona una ciudad";
            // 
            // lblDestino
            // 
            this.lblDestino.AutoSize = true;
            this.lblDestino.Font = new System.Drawing.Font("Roboto", 10F);
            this.lblDestino.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(31)))), ((int)(((byte)(41)))), ((int)(((byte)(55)))));
            this.lblDestino.Location = new System.Drawing.Point(50, 150);
            this.lblDestino.Name = "lblDestino";
            this.lblDestino.Size = new System.Drawing.Size(100, 17);
            this.lblDestino.TabIndex = 3;
            this.lblDestino.Text = "Ciudad Destino:";
            // 
            // cbOrigen
            // 
            this.cbOrigen.Font = new System.Drawing.Font("Roboto", 10F);
            this.cbOrigen.FormattingEnabled = true;
            this.cbOrigen.Items.AddRange(new object[] {
            "Selecciona una ciudad",
            "Bogotá",
            "Medellín",
            "Buenos Aires",
            "Córdoba",
            "Quito",
            "Guayaquil",
            "Cali",
            "Cartagena",
            "Mendoza"});
            this.cbOrigen.Location = new System.Drawing.Point(250, 110);
            this.cbOrigen.Name = "cbOrigen";
            this.cbOrigen.Size = new System.Drawing.Size(300, 24);
            this.cbOrigen.TabIndex = 2;
            this.cbOrigen.Text = "Selecciona una ciudad";
            // 
            // lblOrigen
            // 
            this.lblOrigen.AutoSize = true;
            this.lblOrigen.Font = new System.Drawing.Font("Roboto", 10F);
            this.lblOrigen.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(31)))), ((int)(((byte)(41)))), ((int)(((byte)(55)))));
            this.lblOrigen.Location = new System.Drawing.Point(50, 110);
            this.lblOrigen.Name = "lblOrigen";
            this.lblOrigen.Size = new System.Drawing.Size(100, 17);
            this.lblOrigen.TabIndex = 1;
            this.lblOrigen.Text = "Ciudad Origen:";
            // 
            // btnViewPurchases
            // 
            this.btnViewPurchases.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnViewPurchases.Font = new System.Drawing.Font("Roboto", 10F, System.Drawing.FontStyle.Bold);
            this.btnViewPurchases.ForeColor = System.Drawing.Color.Black;
            this.btnViewPurchases.Location = new System.Drawing.Point(400, 60);
            this.btnViewPurchases.Name = "btnViewPurchases";
            this.btnViewPurchases.Size = new System.Drawing.Size(150, 40);
            this.btnViewPurchases.TabIndex = 0;
            this.btnViewPurchases.Text = "Ver Mis Compras";
            this.btnViewPurchases.UseVisualStyleBackColor = false;
            this.btnViewPurchases.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(191)))), ((int)(((byte)(219)))), ((int)(((byte)(254)))));
            this.btnViewPurchases.Visible = false;
            // 
            // lblFormTitle
            // 
            this.lblFormTitle.AutoSize = true;
            this.lblFormTitle.Font = new System.Drawing.Font("Roboto", 16F, System.Drawing.FontStyle.Bold);
            this.lblFormTitle.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(31)))), ((int)(((byte)(41)))), ((int)(((byte)(55)))));
            this.lblFormTitle.Location = new System.Drawing.Point(220, 20);
            this.lblFormTitle.Name = "lblFormTitle";
            this.lblFormTitle.Size = new System.Drawing.Size(160, 26);
            this.lblFormTitle.TabIndex = 0;
            this.lblFormTitle.Text = "Buscar Vuelos";
            // 
            // SearchFlightsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 550);
            this.Controls.Add(this.contentPanel);
            this.Controls.Add(this.headerPanel);
            this.Name = "SearchFlightsForm";
            this.Text = "Viajecitos SA - Buscar Vuelos";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(241)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            this.headerPanel.ResumeLayout(false);
            this.headerPanel.PerformLayout();
            this.contentPanel.ResumeLayout(false);
            this.formPanel.ResumeLayout(false);
            this.formPanel.PerformLayout();
            this.linkPanel.ResumeLayout(false);
            this.linkPanel.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel headerPanel;
        private System.Windows.Forms.Label lblUser;
        private System.Windows.Forms.Label lblSubtitle;
        private System.Windows.Forms.Label lblTitle;
        private System.Windows.Forms.Panel contentPanel;
        private System.Windows.Forms.Panel formPanel;
        private System.Windows.Forms.Panel linkPanel;
        private System.Windows.Forms.Button btnRegister;
        private System.Windows.Forms.Button btnLogin;
        private System.Windows.Forms.Label lblError;
        private System.Windows.Forms.Button btnSearch;
        private System.Windows.Forms.DateTimePicker datePicker;
        private System.Windows.Forms.Label lblFecha;
        private System.Windows.Forms.ComboBox cbDestino;
        private System.Windows.Forms.Label lblDestino;
        private System.Windows.Forms.ComboBox cbOrigen;
        private System.Windows.Forms.Label lblOrigen;
        private System.Windows.Forms.Button btnViewPurchases;
        private System.Windows.Forms.Label lblFormTitle;
    }
}