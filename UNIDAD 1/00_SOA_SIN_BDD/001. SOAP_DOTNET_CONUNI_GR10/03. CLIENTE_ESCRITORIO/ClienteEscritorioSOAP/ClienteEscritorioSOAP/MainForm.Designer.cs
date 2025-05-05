namespace ClienteEscritorioSOAP
{
    partial class MainForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.txtInput = new MaterialSkin.Controls.MaterialSingleLineTextField();
            this.ddlConversionType = new System.Windows.Forms.ComboBox();
            this.btnConvert = new MaterialSkin.Controls.MaterialRaisedButton();
            this.lblResult = new MaterialSkin.Controls.MaterialLabel();
            this.pictureBoxLogo = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxLogo)).BeginInit();
            this.SuspendLayout();
            // 
            // txtInput
            // 
            this.txtInput.Depth = 0;
            this.txtInput.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtInput.Hint = "Ingrese el valor";
            this.txtInput.Location = new System.Drawing.Point(39, 178);
            this.txtInput.MouseState = MaterialSkin.MouseState.HOVER;
            this.txtInput.Name = "txtInput";
            this.txtInput.PasswordChar = '\0';
            this.txtInput.SelectedText = "";
            this.txtInput.SelectionLength = 0;
            this.txtInput.SelectionStart = 0;
            this.txtInput.Size = new System.Drawing.Size(250, 23);
            this.txtInput.TabIndex = 0;
            this.txtInput.TabStop = false;
            this.txtInput.UseSystemPasswordChar = false;
            // 
            // ddlConversionType
            // 
            this.ddlConversionType.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.ddlConversionType.FormattingEnabled = true;
            this.ddlConversionType.Location = new System.Drawing.Point(39, 222);
            this.ddlConversionType.Name = "ddlConversionType";
            this.ddlConversionType.Size = new System.Drawing.Size(250, 21);
            this.ddlConversionType.TabIndex = 1;
            // 
            // btnConvert
            // 
            this.btnConvert.Depth = 0;
            this.btnConvert.Location = new System.Drawing.Point(39, 318);
            this.btnConvert.MouseState = MaterialSkin.MouseState.HOVER;
            this.btnConvert.Name = "btnConvert";
            this.btnConvert.Primary = true;
            this.btnConvert.Size = new System.Drawing.Size(250, 36);
            this.btnConvert.TabIndex = 2;
            this.btnConvert.Text = "Convertir";
            this.btnConvert.UseVisualStyleBackColor = true;
            this.btnConvert.Click += new System.EventHandler(this.btnConvert_Click);
            // 
            // lblResult
            // 
            this.lblResult.BackColor = System.Drawing.Color.LightBlue;
            this.lblResult.Depth = 0;
            this.lblResult.Font = new System.Drawing.Font("Roboto", 11F);
            this.lblResult.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(222)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
            this.lblResult.Location = new System.Drawing.Point(35, 415);
            this.lblResult.MouseState = MaterialSkin.MouseState.HOVER;
            this.lblResult.Name = "lblResult";
            this.lblResult.Size = new System.Drawing.Size(250, 50);
            this.lblResult.TabIndex = 3;
            this.lblResult.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // pictureBoxLogo
            // 
            this.pictureBoxLogo.Image = ((System.Drawing.Image)(resources.GetObject("pictureBoxLogo.Image")));
            this.pictureBoxLogo.Location = new System.Drawing.Point(324, 69);
            this.pictureBoxLogo.Name = "pictureBoxLogo";
            this.pictureBoxLogo.Size = new System.Drawing.Size(408, 407);
            this.pictureBoxLogo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBoxLogo.TabIndex = 4;
            this.pictureBoxLogo.TabStop = false;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(757, 493);
            this.Controls.Add(this.pictureBoxLogo);
            this.Controls.Add(this.lblResult);
            this.Controls.Add(this.btnConvert);
            this.Controls.Add(this.ddlConversionType);
            this.Controls.Add(this.txtInput);
            this.Name = "MainForm";
            this.Text = "Conversor de Unidades";
            this.Load += new System.EventHandler(this.MainForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxLogo)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private MaterialSkin.Controls.MaterialSingleLineTextField txtInput;
        private System.Windows.Forms.ComboBox ddlConversionType;
        private MaterialSkin.Controls.MaterialRaisedButton btnConvert;
        private MaterialSkin.Controls.MaterialLabel lblResult;
        private System.Windows.Forms.PictureBox pictureBoxLogo;
    }
}
