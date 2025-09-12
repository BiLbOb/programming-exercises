from setuptools import setup, find_packages

setup(
    name="programming-exercises-python",
    version="1.0.0",
    description="Python implementations of programming exercises and algorithms",
    packages=find_packages(where="src"),
    package_dir={"": "src"},
    python_requires=">=3.8",
    install_requires=[],
    extras_require={
        "dev": ["pytest>=7.4.0", "pytest-cov>=4.1.0"]
    }
)