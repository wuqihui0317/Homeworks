% Intrinsic and Extrinsic Camera Parameters
%
% This script file can be directly executed under Matlab to recover the camera intrinsic and extrinsic parameters.
% IMPORTANT: This file contains neither the structure of the calibration objects nor the image coordinates of the calibration points.
%            All those complementary variables are saved in the complete matlab data file Calib_Results.mat.
% For more information regarding the calibration model visit http://www.vision.caltech.edu/bouguetj/calib_doc/


%-- Focal length:
fc = [ 1347.137011230715000 ; 1359.745982932345200 ];

%-- Principal point:
cc = [ 711.048904356724510 ; 518.725401316653690 ];

%-- Skew coefficient:
alpha_c = 0.000000000000000;

%-- Distortion coefficients:
kc = [ -0.020076945105041 ; -0.274608070376976 ; -0.016855161931420 ; 0.007726796274480 ; 0.000000000000000 ];

%-- Focal length uncertainty:
fc_error = [ 33.465233703490462 ; 47.911195746915737 ];

%-- Principal point uncertainty:
cc_error = [ 61.860851601779345 ; 54.567685156179117 ];

%-- Skew coefficient uncertainty:
alpha_c_error = 0.000000000000000;

%-- Distortion coefficients uncertainty:
kc_error = [ 0.095544029567236 ; 0.459565852054473 ; 0.010992834367927 ; 0.012077682614413 ; 0.000000000000000 ];

%-- Image size:
nx = 1440;
ny = 1080;


%-- Various other variables (may be ignored if you do not use the Matlab Calibration Toolbox):
%-- Those variables are used to control which intrinsic parameters should be optimized

n_ima = 9;						% Number of calibration images
est_fc = [ 1 ; 1 ];					% Estimation indicator of the two focal variables
est_aspect_ratio = 1;				% Estimation indicator of the aspect ratio fc(2)/fc(1)
center_optim = 1;					% Estimation indicator of the principal point
est_alpha = 0;						% Estimation indicator of the skew coefficient
est_dist = [ 1 ; 1 ; 1 ; 1 ; 0 ];	% Estimation indicator of the distortion coefficients


%-- Extrinsic parameters:
%-- The rotation (omc_kk) and the translation (Tc_kk) vectors for every calibration image and their uncertainties

%-- Image #1:
omc_1 = [ 1.861737e+00 ; 1.898057e+00 ; -6.627289e-03 ];
Tc_1  = [ -8.625630e+00 ; -2.854397e+01 ; 1.501665e+02 ];
omc_error_1 = [ 3.115910e-02 ; 2.901980e-02 ; 6.436610e-02 ];
Tc_error_1  = [ 6.875592e+00 ; 5.947546e+00 ; 5.036526e+00 ];

%-- Image #2:
omc_2 = [ -2.039310e+00 ; -1.910632e+00 ; -9.139418e-01 ];
Tc_2  = [ -2.234416e+00 ; -7.060208e+00 ; 1.414042e+02 ];
omc_error_2 = [ 2.873879e-02 ; 4.687475e-02 ; 6.634409e-02 ];
Tc_error_2  = [ 6.498818e+00 ; 5.656763e+00 ; 3.755928e+00 ];

%-- Image #3:
omc_3 = [ 1.762677e+00 ; 1.707178e+00 ; -6.673466e-01 ];
Tc_3  = [ -3.780886e+01 ; -4.036587e+01 ; 1.847794e+02 ];
omc_error_3 = [ 2.724051e-02 ; 4.470189e-02 ; 4.975169e-02 ];
Tc_error_3  = [ 8.503835e+00 ; 7.450516e+00 ; 4.579358e+00 ];

%-- Image #4:
omc_4 = [ 1.674476e+00 ; 1.552519e+00 ; -5.826755e-02 ];
Tc_4  = [ -2.156153e+01 ; -4.440450e+01 ; 1.874828e+02 ];
omc_error_4 = [ 3.335982e-02 ; 3.748169e-02 ; 4.672040e-02 ];
Tc_error_4  = [ 8.695968e+00 ; 7.452267e+00 ; 5.528350e+00 ];

%-- Image #5:
omc_5 = [ 1.627773e+00 ; 1.660564e+00 ; -7.400259e-01 ];
Tc_5  = [ -5.829442e+01 ; -1.080749e+01 ; 2.165680e+02 ];
omc_error_5 = [ 2.485309e-02 ; 4.458540e-02 ; 4.710412e-02 ];
Tc_error_5  = [ 9.929083e+00 ; 8.835851e+00 ; 5.259377e+00 ];

%-- Image #6:
omc_6 = [ 1.501520e+00 ; 1.426488e+00 ; -2.692714e-01 ];
Tc_6  = [ -4.587549e+01 ; -6.796589e+00 ; 1.949344e+02 ];
omc_error_6 = [ 3.099616e-02 ; 3.826800e-02 ; 4.185302e-02 ];
Tc_error_6  = [ 9.074366e+00 ; 7.904425e+00 ; 5.037786e+00 ];

%-- Image #7:
omc_7 = [ 1.578334e+00 ; 1.515947e+00 ; -8.389759e-01 ];
Tc_7  = [ -5.179627e+01 ; -1.201769e+01 ; 1.634079e+02 ];
omc_error_7 = [ 2.598844e-02 ; 4.443822e-02 ; 4.483065e-02 ];
Tc_error_7  = [ 7.470939e+00 ; 6.700100e+00 ; 3.937925e+00 ];

%-- Image #8:
omc_8 = [ 1.518091e+00 ; 1.665615e+00 ; -1.452331e+00 ];
Tc_8  = [ -4.974002e+01 ; 1.500051e+01 ; 2.239957e+02 ];
omc_error_8 = [ 2.397432e-02 ; 4.851417e-02 ; 5.111439e-02 ];
Tc_error_8  = [ 1.024964e+01 ; 9.157155e+00 ; 5.044926e+00 ];

%-- Image #9:
omc_9 = [ 1.674480e+00 ; 1.378243e+00 ; -4.012555e-01 ];
Tc_9  = [ -4.415639e+01 ; -2.863289e+01 ; 1.753098e+02 ];
omc_error_9 = [ 3.159181e-02 ; 4.094129e-02 ; 4.356298e-02 ];
Tc_error_9  = [ 8.103294e+00 ; 7.067881e+00 ; 4.541211e+00 ];

