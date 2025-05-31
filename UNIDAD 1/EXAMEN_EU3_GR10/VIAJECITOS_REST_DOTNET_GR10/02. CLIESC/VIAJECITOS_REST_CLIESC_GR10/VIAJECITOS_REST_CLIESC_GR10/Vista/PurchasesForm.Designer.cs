namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    partial class PurchasesForm
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
            this.lblSubtitle = new System.Windows.Forms.Label();
            this.lblTitle = new System.Windows.Forms.Label();
            this.contentPanel = new System.Windows.Forms.Panel();
            this.tablePanel = new System.Windows.Forms.Panel();
            this.linkPanel = new System.Windows.Forms.Panel();
            this.btnBack = new System.Windows.Forms.Button();
            this.lblError = new System.Windows.Forms.Label();
            this.table = new System.Windows.Forms.DataGridView();
            this.IdCompra = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Vuelo = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Valor = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.FechaCompra = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.HoraSalida = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.lblFormTitle = new System.Windows.Forms.Label();
            this.headerPanel.SuspendLayout();
            this.contentPanel.SuspendLayout();
            this.tablePanel.SuspendLayout();
            this.linkPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.table)).BeginInit();
            this.SuspendLayout();
            // 
            // headerPanel
            // 
            this.headerPanel.Controls.Add(this.lblSubtitle);
            this.headerPanel.Controls.Add(this.lblTitle);
            this.headerPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.headerPanel.Location = new System.Drawing.Point(0, 0);
            this.headerPanel.Name = "headerPanel";
            this.headerPanel.Size = new System.Drawing.Size(800, 120);
            this.headerPanel.TabIndex = 0;
            this.headerPanel.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(163)))), ((int)(((byte)(191)))), ((int)(((byte)(250)))));
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
            this.contentPanel.Controls.Add(this.tablePanel);
            this.contentPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.contentPanel.Location = new System.Drawing.Point(0, 120);
            this.contentPanel.Name = "contentPanel";
            this.contentPanel.Size = new System.Drawing.Size(800, 430);
            this.contentPanel.TabIndex = 1;
            this.contentPanel.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(241)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // tablePanel
            // 
            this.tablePanel.Controls.Add(this.linkPanel);
            this.tablePanel.Controls.Add(this.lblError);
            this.tablePanel.Controls.Add(this.table);
            this.tablePanel.Controls.Add(this.lblFormTitle);
            this.tablePanel.Location = new System.Drawing.Point(100, 50);
            this.tablePanel.Name = "tablePanel";
            this.tablePanel.Size = new System.Drawing.Size(600, 400);
            this.tablePanel.TabIndex = 0;
            this.tablePanel.BackColor = System.Drawing.Color.White;
            this.tablePanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            // 
            // linkPanel
            // 
            this.linkPanel.Controls.Add(this.btnBack);
            this.linkPanel.Location = new System.Drawing.Point(0, 350);
            this.linkPanel.Name = "linkPanel";
            this.linkPanel.Size = new System.Drawing.Size(600, 50);
            this.linkPanel.TabIndex = 3;
            // 
            // btnBack
            // 
            this.btnBack.AutoSize = true;
            this.btnBack.FlatAppearance.BorderSize = 0;
            this.btnBack.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnBack.Font = new System.Drawing.Font("Roboto", 9F);
            this.btnBack.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(59)))), ((int)(((byte)(130)))), ((int)(((byte)(246)))));
            this.btnBack.Location = new System.Drawing.Point(250, 10);
            this.btnBack.Name = "btnBack";
            this.btnBack.Size = new System.Drawing.Size(100, 30);
            this.btnBack.TabIndex = 0;
            this.btnBack.Text = "Volver a Buscar";
            this.btnBack.UseVisualStyleBackColor = true;
            // 
            // lblError
            // 
            this.lblError.AutoSize = true;
            this.lblError.Font = new System.Drawing.Font("Roboto", 9F);
            this.lblError.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(220)))), ((int)(((byte)(38)))), ((int)(((byte)(38)))));
            this.lblError.Location = new System.Drawing.Point(50, 310);
            this.lblError.Name = "lblError";
            this.lblError.Size = new System.Drawing.Size(500, 20);
            this.lblError.TabIndex = 2;
            this.lblError.Text = "";
            this.lblError.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // table
            // 
            this.table.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.table.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.IdCompra,
            this.Vuelo,
            this.Valor,
            this.FechaCompra,
            this.HoraSalida});
            this.table.Location = new System.Drawing.Point(50, 50);
            this.table.Name = "table";
            this.table.Size = new System.Drawing.Size(500, 250);
            this.table.TabIndex = 1;
            // 
            // IdCompra
            // 
            this.IdCompra.HeaderText = "ID Compra";
            this.IdCompra.Name = "IdCompra";
            this.IdCompra.Width = 100;
            // 
            // Vuelo
            // 
            this.Vuelo.HeaderText = "Vuelo";
            this.Vuelo.Name = "Vuelo";
            this.Vuelo.Width = 150;
            // 
            // Valor
            // 
            this.Valor.HeaderText = "Valor";
            this.Valor.Name = "Valor";
            this.Valor.Width = 100;
            // 
            // FechaCompra
            // 
            this.FechaCompra.HeaderText = "Fecha Compra";
            this.FechaCompra.Name = "FechaCompra";
            this.FechaCompra.Width = 120;
            // 
            // HoraSalida
            // 
            this.HoraSalida.HeaderText = "Hora Salida";
            this.HoraSalida.Name = "HoraSalida";
            this.HoraSalida.Width = 120;
            // 
            // lblFormTitle
            // 
            this.lblFormTitle.AutoSize = true;
            this.lblFormTitle.Font = new System.Drawing.Font("Roboto", 16F, System.Drawing.FontStyle.Bold);
            this.lblFormTitle.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(31)))), ((int)(((byte)(41)))), ((int)(((byte)(55)))));
            this.lblFormTitle.Location = new System.Drawing.Point(200, 10);
            this.lblFormTitle.Name = "lblFormTitle";
            this.lblFormTitle.Size = new System.Drawing.Size(200, 26);
            this.lblFormTitle.TabIndex = 0;
            this.lblFormTitle.Text = "Mis Compras";
            // 
            // PurchasesForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 550);
            this.Controls.Add(this.contentPanel);
            this.Controls.Add(this.headerPanel);
            this.Name = "PurchasesForm";
            this.Text = "Viajecitos SA - Mis Compras";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(241)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            this.headerPanel.ResumeLayout(false);
            this.headerPanel.PerformLayout();
            this.contentPanel.ResumeLayout(false);
            this.tablePanel.ResumeLayout(false);
            this.tablePanel.PerformLayout();
            this.linkPanel.ResumeLayout(false);
            this.linkPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.table)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel headerPanel;
        private System.Windows.Forms.Label lblSubtitle;
        private System.Windows.Forms.Label lblTitle;
        private System.Windows.Forms.Panel contentPanel;
        private System.Windows.Forms.Panel tablePanel;
        private System.Windows.Forms.Panel linkPanel;
        private System.Windows.Forms.Button btnBack;
        private System.Windows.Forms.Label lblError;
        private System.Windows.Forms.DataGridView table;
        private System.Windows.Forms.DataGridViewTextBoxColumn IdCompra;
        private System.Windows.Forms.DataGridViewTextBoxColumn Vuelo;
        private System.Windows.Forms.DataGridViewTextBoxColumn Valor;
        private System.Windows.Forms.DataGridViewTextBoxColumn FechaCompra;
        private System.Windows.Forms.DataGridViewTextBoxColumn HoraSalida;
        private System.Windows.Forms.Label lblFormTitle;
    }
}