import os

ant_home = pathvar('ANT_HOME')
ant_home.set(repr(pathvar('EOS_PRODUCT_ROOT')) + os.sep + 'apache-ant-1.7.0')

java_home = pathvar('JAVA_HOME')
java_home.set(repr(pathvar('EOS_PRODUCT_ROOT')) + os.sep + 'jdk-1.5.0')

install_dir = pathvar('TRIFORK_INSTALL_DIR')
#install_dir.set('u:/projects/devel/easnt/release/4.1/install')
install_dir.set('/home/mgr/devel/easnt/release/4.1/install')

domain_dir = pathvar('TRIFORK_DOMAIN_DIR')
domain_dir.set('/home/mgr/domains/fmed')

path = pathvar('PATH')
path.append(repr(java_home) + os.sep + 'bin')
path.append(repr(ant_home) + os.sep + 'bin')
path.append(repr(domain_dir) + os.sep + 'bin')
path.append(repr(install_dir) + os.sep + 'bin')
